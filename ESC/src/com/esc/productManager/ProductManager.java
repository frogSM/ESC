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
	private boolean isChangeCart;
	private Object addProductCount;
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
			Thread.sleep(3000);
			numBytesCurrnetRead = port.read(currentRxBuffer, 1000) ;
		} catch ( IOException | InterruptedException e ) {
			e.printStackTrace();
		}
		
		
		
		
		//카트에 상품이 추가되었을때.
		if( numBytesPreviousRead < numBytesCurrnetRead ) { 
			this.addProductCount = ( numBytesCurrnetRead - numBytesPreviousRead ) / 12;
			this.isChangeCart = true;
		}
		
		//카트에서 상품을 뺐을때.
		else if( numBytesPreviousRead > numBytesCurrnetRead ) { 
			this.subtractProductCount = ( numBytesPreviousRead - numBytesCurrnetRead ) / 12;
			this.isChangeCart = true;
		}
		
		//카트 상품이 변함 없을
		else if( numBytesPreviousRead == numBytesCurrnetRead ){
			this.isChangeCart = false;
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
		}
		
		for ( int i = 0 ; i < tagCount -1 ; i ++ ) { 
			this.taggedUIDs.add( HexDump.toHexString( dividedByteBuffer[ i ] ) ) ;
		}
		
		return this.taggedUIDs;
	}
}
