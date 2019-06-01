package com.example.acer.pocjil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ServicesAdapter extends BaseAdapter {
    ArrayList<ServiceExpertSpace> serviceDetails;
    Context context;
    LayoutInflater layoutInflater;

    public ServicesAdapter(Context context, ArrayList<ServiceExpertSpace> serviceDetails){
        this.serviceDetails = serviceDetails;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return serviceDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return serviceDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.service_space,null);
        TextView txtCat,txtCity,txtState,txtTitle, txtUrl;
        ImageView txtImage;
        txtCat = view.findViewById(R.id.cat_title);
        txtCity = view.findViewById(R.id.cityTitle);
        txtImage = view.findViewById(R.id.imageService);
        txtTitle = view.findViewById(R.id.titleService);
        txtUrl = view.findViewById(R.id.urlService);
        txtState = view.findViewById(R.id.stateTitle);


        txtCat.setText(serviceDetails.get(i).getCat_title());
        txtCity.setText(serviceDetails.get(i).getCity().getTitle());
        txtState.setText(serviceDetails.get(i).getState().getTitle());
        txtUrl.setText(serviceDetails.get(i).getUrlname());
        txtTitle.setText(serviceDetails.get(i).getName());


        String imgURL = "https://www.kesbokar.com.au/uploads/yellowpage/" + serviceDetails.get(i).getImageLogo();
        new ServicesAdapter.DownLoadImageTask(txtImage).execute(imgURL);

        return view;
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
