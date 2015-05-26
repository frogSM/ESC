package com.esc.Connection;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import android.content.Context;
import android.database.Observable;

import com.esc.Constants;
import com.esc.CustomerNotice.Notice;
import com.esc.CustomerQuestionAndAnswer.QuestionAndAnswer;
import com.esc.productManager.Product;
import com.esc.searchProduct.RecommendProduct;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonHelper extends Observable {
	private static JsonHelper instance;
	private Context mContext;
	
	private String mMessage;
	
	private String mType;
	private Object mObject;
	
	public static synchronized JsonHelper getInstance(Context mContext) {
		if(instance == null) {
			instance = new JsonHelper(mContext);
		}
		return instance;
	}
	
	public JsonHelper(Context mContext) {
		this.mContext = mContext;
	}
	
	/** ���Ÿ�԰� �����͸� �Ű������� �޾Ƽ� ���Ÿ�Կ� ���� JSON������ �����. **/
	public String makeJsonMessage(String msgType, Object data) {

		JSONObject jsonObj = new JSONObject();

		switch (msgType) {
		case Constants.Uid_Info:
			jsonObj.put("type", "Uid_Info");
			
			ArrayList<String> castingData = new ArrayList<String>();
			castingData =(ArrayList<String>)data;

			jsonObj.put("uid", castingData);
			break;
			
		case Constants.All_Product_Info :
			jsonObj.put("type", "All_Product_Info");
			break;

		case Constants.requestNoticeDB :
			jsonObj.put("type", "requestNoticeDB");
			break;
			
		case Constants.requestBest5QADB :
			jsonObj.put("type", "requestBest5QADB");
			break;
			
		case Constants.RecommendedProduct_Info :
			jsonObj.put("type", "RecommendedProduct_Info");
			jsonObj.put("number", (int)data);
			break;
		
		}
		

		return jsonObj.toString();
	}
	
	/** JSON������ �̿��Ͽ� ���Ÿ���� �����ϰ� �׿� ���� ��ü�� ��ȯ�Ѵ�. **/
	public Object parserJsonMessage(String json_msg) {
		
		mMessage = json_msg;
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = parser.parse(mMessage).getAsJsonObject();
		
		mType = gson.fromJson(object.get("type"), String.class);
		switch(mType) {
		
		case Constants.Uid_Info :
			// List<Product>�� ��ȯ�� ��ü�� ������Ʈ�� ��ȯ�� �ٽ� ��ȯ(������ ����)
			List<Product> products = new ArrayList<Product>();
			products = gson.fromJson(object.get("Object"), new TypeToken<List<Product>>(){}.getType());
			mObject = products;
			break;
			
		case Constants.All_Product_Info :
			List<Product> allProducts = new ArrayList<Product>();
			allProducts = gson.fromJson(object.get("Object"), new TypeToken<List<Product>>(){}.getType());
			mObject = allProducts;
			break;
		
		case Constants.requestNoticeDB :
			List<Notice> notices = new ArrayList<Notice> ( );
			notices = gson.fromJson(object.get("Object"), new TypeToken<List<Notice>>(){}.getType());
			mObject = notices;
			break;
		
		case Constants.requestBest5QADB :
			List<QuestionAndAnswer> questionAndAndswers = new ArrayList<QuestionAndAnswer> ( ) ;
			questionAndAndswers = gson.fromJson(object.get("Object"), new TypeToken<List<QuestionAndAnswer>>(){}.getType());
			mObject = questionAndAndswers;
			break;
		
		case Constants.RecommendedProduct_Info :
			List<RecommendProduct> recommendProducts = new ArrayList<RecommendProduct>();
			recommendProducts = gson.fromJson(object.get("Object"), new TypeToken<List<RecommendProduct>>(){}.getType());
			mObject = recommendProducts;
			break;
		}
		
		return mObject;
	}
	
	/** ��� Ÿ���� ��ȯ�Ѵ�. **/
	public String getType() {
		return mType;
	}
	
	/** ������ ��ü�� ��ȯ�Ѵ�. **/
	public Object getObject() {
		return mObject;
	}
}
