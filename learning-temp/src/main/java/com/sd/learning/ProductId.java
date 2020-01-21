package com.sd.learning;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子签章产品级分类，与产品编号有所不同
 * @author yuanfeng36
 *
 */
public enum ProductId {
	XIAOAN("XA"),//小安
	EMPLOY("ED001"),//员工贷
	MEMBER("ED002"),//会员贷
	WANXH_LIMIT("WXH01"),//万象花申请阶段额度协议
	WANXH_PIROD("WXH02"),//万象花分期协议
	WANXH_BILL("WXH03"),//万象花出账单协议
	KADAI("KADAI"),//卡贷
	EMPLOY_DEBJ("011"),//员工贷2.0 等额本金
	EMPLOY_DEBX("012"),//员工贷2.0 等本等息

	RZD_A("008"),
	RZD_B("009"),
	RZD_C("010"),
	RZD_D("013"),
	GAS("014"),// 润心贷(燃气贷)
    RZD_E("015"),
	EMPLOY_TO_GAS("016"),//员工贷转润心贷
	;

	private String code;
	public static List<ProductId> productList = new ArrayList<ProductId>();
	
	public static List<ProductId> getProductList() {
	    if(productList.size() == 0){
    	    productList.add(ProductId.XIAOAN);
    	    productList.add(ProductId.EMPLOY);
    	    productList.add(ProductId.MEMBER);
    	    productList.add(ProductId.WANXH_LIMIT);
    	    productList.add(ProductId.WANXH_PIROD);
    	    productList.add(ProductId.WANXH_BILL);
    	    productList.add(ProductId.KADAI);
    	    productList.add(ProductId.EMPLOY_DEBJ);
    	    productList.add(ProductId.EMPLOY_DEBX);
    	    productList.add(ProductId.RZD_A);
    	    productList.add(ProductId.RZD_B);
    	    productList.add(ProductId.RZD_C);
    	    productList.add(ProductId.RZD_D);
    	    productList.add(ProductId.RZD_E);
            productList.add(ProductId.GAS);
            productList.add(ProductId.EMPLOY_TO_GAS);

	    }
        return productList;
    }

    public void setProductList(List<ProductId> productList) {
		ProductId.productList = productList;
    }

    private ProductId(String code){
		this.code = code;
	}
	
	public String value(){
		return code;
	}
	
}
