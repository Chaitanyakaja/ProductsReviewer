package volleyapp2.example.com.project258_1;

import java.io.Serializable;

/**
 * Created by katto on 5/9/2017.
 */

public class ModelListOfProducts implements Serializable {
    String imageSRC;
    String productName;
    String description;
    String productID;
    String productUPC;
    String price;
    String productURL;

    public ModelListOfProducts() {
    }

    public ModelListOfProducts(String imageSRC,
                               String productName,
                               String description,
                               String productID,
                               String productUPC,
                               String price,
                               String productURL) {
        this.imageSRC = imageSRC;
        this.productName = productName;
        this.description = description;
        this.productID = productID;
        this.productUPC = productUPC;
        this.price = price;
        this.productURL = productURL;
    }


    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductUPC() {
        return productUPC;
    }

    public void setProductUPC(String productUPC) {
        this.productUPC = productUPC;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getImageSRC() {
        return imageSRC;
    }

    public void setImageSRC(String imageSRC) {
        this.imageSRC = imageSRC;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
