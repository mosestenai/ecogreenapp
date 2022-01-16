package com.example.ecogreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Anime {
    private String itemname;
    private String description;
    private String username;
    private String price;
    private String url;
    private String shop;
    private String location;
    private String phone;
    private String heading;
    private String centre;
    private  String type;
    private String question;
    private String answer;
    private String link;
    private String id;
    private String feedback;
    private String time;
    private String item;
    private String amount;
    private String date;

    public Anime(){

    }

    public Anime(String feedback,String time) {
        this.feedback = feedback;
        this.time = time;
    }

    public Anime(String item, String amount, String phone, String date) {
        this.item = item;
        this.amount = amount;
        this.phone = phone;
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public static Anime getitems(JSONObject jsonObject) throws JSONException {
        String itemname = jsonObject.getString("item");
        String description = jsonObject.getString("description");
        String price = jsonObject.getString("price");
        String username = jsonObject.getString("username");
        String url = jsonObject.getString("url");

        return new Anime(itemname,description,price,username,url);
    }
    public static Anime getfaqs(JSONObject jsonObject) throws  JSONException{
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        String id = jsonObject.getString("id");

        return new Anime(question,answer,id);
    }
    public static Anime getfeebacks(JSONObject jsonObject) throws  JSONException{
        String feedback = jsonObject.getString("feedback");
        String time = jsonObject.getString("time");


        return new Anime(feedback,time);
    }
    public static Anime gettransactions(JSONObject jsonObject) throws  JSONException{
        String item = jsonObject.getString("item");
        String amount = jsonObject.getString("amount");
        String phone = jsonObject.getString("phone");
        String date = jsonObject.getString("date");


        return new Anime(item,amount,phone,date);
    }
    public static Anime getShops(JSONObject jsonObject) throws JSONException {
        String shop = jsonObject.getString("shop");
        String description = jsonObject.getString("description");
        String phone = jsonObject.getString("phone");
        String username = jsonObject.getString("username");
        String location = jsonObject.getString("location");
        String url = jsonObject.getString("url");

        return new Anime(shop,description,phone,username,location,url);
    }
    public static Anime getIdeas(JSONObject jsonObject) throws JSONException {
        String heading = jsonObject.getString("heading");
        String description = jsonObject.getString("description");
        String phone = jsonObject.getString("phone");
        String link = jsonObject.getString("link");
        String url = jsonObject.getString("url");
        String none = "none";
        String none2 = "none";

        return new Anime(heading,description,phone,link,url,none,none2);
    }
    public static Anime getCentres(JSONObject jsonObject) throws JSONException{
        String centre = jsonObject.getString("centre");
        String description = jsonObject.getString("description");
        String phone = jsonObject.getString("contact");
        String type = jsonObject.getString("type");
        String location = jsonObject.getString("location");
        String url = jsonObject.getString("url");
        String none = "none";
        String none2 = "none";
        String none3 = "none";

        return new Anime(centre,description,phone,type,location,url,none,none2,none3);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Anime(String question, String answer,String id) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
    public Anime(String centre, String description, String phone, String type,String location,String url,String none,
                 String none2,String none3) {
        this.centre = centre;
        this.description = description;
        this.location = location;
        this.type = type;
        this.phone = phone;
        this.url = url;
    }

    public Anime(String heading, String description, String phone, String link,String url,String none,String none2) {
        this.heading = heading;
        this.description = description;
        this.phone = phone;
        this.link = link;
        this.url = url;
    }

    public Anime(String shop, String description, String phone, String username, String location,String url) {
        this.shop = shop;
        this.description = description;
        this.phone = phone;
        this.location = location;
        this.username = username;
        this.url = url;
    }



    public Anime(String itemname, String description, String price, String username, String url) {
        this.itemname = itemname;
        this.description = description;
        this.username = username;
        this.price = price;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
