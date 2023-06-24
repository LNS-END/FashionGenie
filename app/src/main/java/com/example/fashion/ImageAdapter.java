package com.example.fashion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> imagePaths;
    // ImageAdapter 클래스의 멤버 변수
    private List<String> selectedImagePaths;
    public ImageAdapter(Context context) {
        this.context = context;
    }
    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return imagePaths != null ? imagePaths.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return imagePaths != null ? imagePaths.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            holder.imageView = convertView.findViewById(R.id.image_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 이미지 파일을 Bitmap으로 로드하여 ImageView에 설정
        String imagePath = imagePaths.get(position);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        holder.imageView.setImageBitmap(bitmap);

        return convertView;
    }
    private static class ViewHolder {
        ImageView imageView;
    }

    public void toggleSelection(int position) {
        String imagePath = imagePaths.get(position);

        if (selectedImagePaths.contains(imagePath)) {
            selectedImagePaths.remove(imagePath);
        } else {
            selectedImagePaths.add(imagePath);
        }

        notifyDataSetChanged();
    }
    public void removeSelectedImages() {
        for (String imagePath : selectedImagePaths) {
            imagePaths.remove(imagePath);
        }

        selectedImagePaths.clear();
    }
    public List<String> getSelectedImagePaths() {
        return selectedImagePaths;
    }
}


