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

		//Port����
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
	
	//īƮ�� �ִ� ��ǰ�� �ʱ�
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
			/** �ߺ� ó�� **/
			
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
	
	/** īƮ�� ��� ��� ��ǰ�� ���� ��� **/
	public String GetTotalAccount ( ArrayList< Product > products ) {
		
		String totalAccount = null;
		
		/** 1. �±׵� ��ǰ���� �Է¹޴´�. **/
		int productLength = products.size();
		int totalAccountInteger = 0;
		int eachPriceInteger;
		
		String eachPrice;
		
		/** 2. �±׵� ��ǰ���� ������ŭ �ݺ��Ѵ�. **/
		for ( int i = 0 ; i < productLength ; i ++ ) { 
			
			/** 3. ��ǰ�� ������ ���Ѵ�. **/
			eachPrice = products.get(i).getPriceNow();
			eachPriceInteger = Integer.parseInt(eachPrice);
			
			/** 4. �����Ͽ� ���Ѵ�. **/
			totalAccountInteger += eachPriceInteger;
		}
		
		totalAccount = Integer.toString(totalAccountInteger);
		
		/** 5. ��ǰ ������ ����Ѵ�. **/
		return totalAccount;
	}
	
	/** productsInCart ���� **/
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
			
			/** �̴ϰ�å2�� ������ �̴ϰ�å ������ �ϳ� ������Ų��.
			 * 	�׸��� �̴ϰ�å2�� �Է¹��� products���� �̴ϰ�å2�� �����Ѵ�.
			 * 	���������� productsInCart�� �߰���Ű�� �ʴ´�. **/
			/*
			if( product.getName().equals("�̴ϰ�å2") == true ) {
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
	
	
	/** ���� īƮ�� ����� ��ǰ ��ȯ **/
	public ArrayList<CustomerCart> getCustomerCart (  ) {
		
		
		return this.productsInCart;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
