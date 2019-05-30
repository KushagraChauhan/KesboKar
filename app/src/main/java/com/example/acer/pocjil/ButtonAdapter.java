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

public class ButtonAdapter extends BaseAdapter {
    ArrayList<ButtonsDetails> btnDetails;
    Context context;
    LayoutInflater layoutInflater;

    public ButtonAdapter(Context context, ArrayList<ButtonsDetails> btnDetails){
        this.btnDetails = btnDetails;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return btnDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return btnDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_main,null);
        TextView txtId,txtUrl,txtIcon,txtTitle;
        ImageView txtImage;
        txtIcon = view.findViewById(R.id.icon);
        txtId = view.findViewById(R.id.id);
        txtImage = view.findViewById(R.id.image);
        txtTitle = view.findViewById(R.id.title);
        txtUrl = view.findViewById(R.id.url);
        txtIcon.setText(btnDetails.get(i).getIcon());
        txtId.setText(btnDetails.get(i).getId() + "");
        String imgURL = "https://www.kesbokar.com.au/uploads/category/" + btnDetails.get(i).getImage();
        new DownLoadImageTask(txtImage).execute(imgURL);

        txtTitle.setText(btnDetails.get(i).getTitle());
        txtUrl.setText(btnDetails.get(i).getUrl());
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
