package com.esc.productManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.HexDump;
import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.widget.Toast;
import driver.UsbSerialDriver;
import driver.UsbSerialPort;
import driver.UsbSerialProber;

public class ProductManager {
	
	UsbManager usbManager;
	List<UsbSerialDriver> availableDrivers;
    private UsbSerialPort port;
    UsbDeviceConnection connection;
    UsbSerialDriver driver;
	
	Context context;
	
	ArrayList<String> taggedUIDs;
	ArrayList<CustomerCart> productsInCart;
	private boolean isCartChanged;
	private int addProductCount;
	private int subtractProductCount;
	
	//Constructor
	public ProductManager( Context context ) {
		
		this.context = context;
	}
	
	//SericalCommunication
	public boolean OpenSerialPort ( )  {
		
		boolean ret = false;
		// Find all available drivers from attached devices.
		usbManager = (UsbManager) this.context.getSystemService(Context.USB_SERVICE);
		
		availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(usbManager);

		if (availableDrivers.isEmpty()) {
		  ret = false;
		}
		// Open a connection to the first available driver.
		driver = availableDrivers.get(0);
		
		connection = usbManager.openDevice(driver.getDevice());
		if (connection == null) {
			ret = false;
		}
		else{
			ret = true;
		}
		// Read some data! Most have just one port (port 0).

		port = driver.getPorts().get(0);

		//Port¿­
		try {
			port.open(connection);
			port.setParameters( 9600 , port.DATABITS_8, port.STOPBITS_1,port.PARITY_NONE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( ret == false ) {
			Toast.makeText(this.context.getApplicationContext(), "CONNECTION FAIL", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this.context.getApplicationContext(), "CONNECTION SUCCESS", Toast.LENGTH_SHORT).show();
		}
		
		
		return ret;
	}
	
	public boolean CloseSerialPort( ) {
		boolean ret = false;
		if( this.port != null ) {
			try {
				port.close();
				ret = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = false;
			}
		}
		
		return ret;
	}
	
	//카트에 있는 상품들 초기
	public ArrayList<String> GetTaggedUIDs( ) {
		
		this.taggedUIDs = new ArrayList<String> ( );
	
		byte txBuffer [ ] = new byte [4];
		byte currentRxBuffer [ ] = new byte [128];
		
		txBuffer[0] = 0x04;
		txBuffer[1] = 0x00;
		txBuffer[2] = 0x40;
		txBuffer[3] = (byte)0xff;
		
		int numBytesCurrnetRead = 0;
		int numBytesPreviousRead = 0;
		
		try { 
			port.write( txBuffer, 1000 );
			Thread.sleep(1000);
			numBytesCurrnetRead = port.read(currentRxBuffer, 1000) ;
		} catch ( IOException | InterruptedException e ) {
			e.printStackTrace();
		}
	
		byte[ ] [ ] dividedByteBuffer = new byte [20][12];
		
		int tagCount = 0;
		int k = 0;
		
		for( int i = 0 ; i < numBytesCurrnetRead ; i++ ) {
			dividedByteBuffer[ tagCount ][ k ] = currentRxBuffer [ i ];
			k++;
			if ( currentRxBuffer [ i ] == (byte) 0xff ) { 
				tagCount++;
				k = 0;
			}
			/** 중복 처리 **/
			
		}
		
		for ( int i = 0 ; i < tagCount -1 ; i ++ ) { 
			
			this.taggedUIDs.add( HexDump.toHexString( dividedByteBuffer[ i ] ) ) ;
		}
		
		return this.taggedUIDs;
	}
	
	public int GetAddProductCount ( ) { 
		return this.addProductCount;
	}
	
	public int GetSubtractCount ( ) { 
		return this.subtractProductCount;
	}
	
	public boolean GetIsCartChanged ( ) { 
		return this.isCartChanged;
	}
	
	/** 카트에 담긴 모든 상품의 가격 계산 **/
	public String GetTotalAccount ( ArrayList< Product > products ) {
		
		String totalAccount = null;
		
		/** 1. 태그된 상품들을 입력받는다. **/
		int productLength = products.size();
		int totalAccountInteger = 0;
		int eachPriceInteger;
		
		String eachPrice;
		
		/** 2. 태그된 상품들의 개수만큼 반복한다. **/
		for ( int i = 0 ; i < productLength ; i ++ ) { 
			
			/** 3. 상품의 가격을 구한다. **/
			eachPrice = products.get(i).getPriceNow();
			eachPriceInteger = Integer.parseInt(eachPrice);
			
			/** 4. 누적하여 더한다. **/
			totalAccountInteger += eachPriceInteger;
		}
		
		totalAccount = Integer.toString(totalAccountInteger);
		
		/** 5. 상품 가격을 출력한다. **/
		return totalAccount;
	}
	
	/** productsInCart 갱신 **/
	public ArrayList<Product> RenewalProductsInCart ( ArrayList<Product> products ) {
		
		productsInCart = new ArrayList<CustomerCart> ( );
		
		
		ArrayList<Product> returnProducts = products;
		int flag = 0;
		
		for ( int i = 0 ; i < products.size() ; i ++ ) {
			
			
			Product product = products.get(i);
			String strNumber = Integer.toString(product.getNumber());
			
			CustomerCart customerCart = new CustomerCart ( strNumber, 
					product.getName() , 
					product.getPriceNow() ,
					"1",
					product.getManufacturer(),
					product.getType() );
			
			/** 미니공책2가 들어오면 미니공책 수량을 하나 증가시킨다.
			 * 	그리고 미니공책2는 입력받은 products에서 미니공책2를 제거한다.
			 * 	마찬가지로 productsInCart에 추가시키지 않는다. **/
			/*
			if( product.getName().equals("미니공책2") == true ) {
				customerCart.setCount("2");
				returnProducts.remove(i);
				flag = 1;
			}

			if( flag == 0 ) {
			}
			//*/
			this.productsInCart.add(customerCart);
		}
		
		return returnProducts;
	}
	
	
	/** 현재 카트에 저장된 상품 반환 **/
	public ArrayList<CustomerCart> getCustomerCart (  ) {
		
		
		return this.productsInCart;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
