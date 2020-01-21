import com.alibaba.fastjson.JSON;
import com.sd.learning.ProductId;

/**
 * @author SONGDING
 * @Description
 * @date 2019/5/5 18:58
 */
public class AAA {
    public static void main(String[] args) {
        String type = "6";
        switch (type){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                System.out.println("type value is "+type);
                break;
            default:
                System.out.println("this is default!");

        }


        System.out.println("ProductId = [" + JSON.toJSONString(ProductId.EMPLOY_DEBX) + "]");


    }
}
