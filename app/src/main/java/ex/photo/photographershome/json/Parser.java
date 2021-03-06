package ex.photo.photographershome.json;

import ex.photo.photographershome.model.Photographer;
import ex.photo.photographershome.model.PhotographerImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Photographer> parseStringToJson(String data) {
        List<Photographer> mPhotographers;

        try {
            JSONArray jsonArray =new JSONArray(data);
            mPhotographers = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject photographerJsonObject = jsonArray.getJSONObject(i);
                String Photographer_ID = photographerJsonObject.optString("Photographer_ID");
                String PhotographerName = photographerJsonObject.optString("PhotographerName");
                String Location =  photographerJsonObject.optString("Location");
                String Facebook = photographerJsonObject.optString("Facebook");
                String Behance = photographerJsonObject.optString("Behance");
                String WhatsApp = photographerJsonObject.optString("WhatsApp");
                String Instagram = photographerJsonObject.optString("Instagram");
                String ProfileImage = photographerJsonObject.optString("ProfileImage");



                Photographer photographer = new Photographer();
                photographer.setPhotographer_ID(Photographer_ID);
                photographer.setPhotographerName(PhotographerName);
                photographer.setLocation(Location);
                photographer.setFacebook(Facebook);
                photographer.setBehance(Behance);
                photographer.setWhatsApp(WhatsApp);
                photographer.setInstagram(Instagram);
                photographer.setProfileImage(ProfileImage);

                mPhotographers.add(photographer);


            }

            return mPhotographers;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static List<PhotographerImage> parseStringToJsonforPhotographerImages(String data) {
        List<PhotographerImage> mPhotographers;

        try {
            JSONArray jsonArray =new JSONArray(data);
            mPhotographers = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject photographerJsonObject = jsonArray.getJSONObject(i);
                String Catagory = photographerJsonObject.optString("Catagory");
                String Image = photographerJsonObject.optString("Image");


                PhotographerImage photographer = new PhotographerImage();
                photographer.setCatagory(Catagory);
                photographer.setImage(Image);

                mPhotographers.add(photographer);


            }

            return mPhotographers;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static List<String> parseStringToJsonforPhotographerLocations(String data) {
        List<String> mPhotographers;

        try {
            JSONArray jsonArray =new JSONArray(data);
            mPhotographers = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject photographerJsonObject = jsonArray.getJSONObject(i);
                String Location = photographerJsonObject.optString("Location");
                mPhotographers.add(Location);



            }

            return mPhotographers;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
