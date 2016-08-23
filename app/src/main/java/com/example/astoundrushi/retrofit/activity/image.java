package com.example.astoundrushi.retrofit.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.astoundrushi.retrofit.R;
import com.example.astoundrushi.retrofit.model.MovieDetails;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link image.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link image#newInstance} factory method to
 * create an instance of this fragment.
 */
public class image extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private DisplayImageOptions options;

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public image()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment image.
     */
    // TODO: Rename and change types and number of parameters
    public static image newInstance(String param1,MovieDetails resultMovie)
    {
        image fragment = new image();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable("movieresult",resultMovie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .showImageOnFail(android.R.drawable.ic_dialog_alert)
                .showImageForEmptyUri(android.R.drawable.ic_dialog_alert)
                .displayer(new RoundedBitmapDisplayer(0)).build();

        View v = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView poster = (ImageView) v.findViewById(R.id.imgPoster);
        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sendMovie=new Intent(getContext(),MovieDetailActivity.class);
                sendMovie.putExtra("movie",getArguments().getSerializable("movieresult"));
                startActivity(sendMovie);
            }
        });
        ImageLoader.getInstance().displayImage(mParam1, poster, options, new SimpleImageLoadingListener()
        {
            @Override
            public void onLoadingStarted(String imageUri, View view)
            {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason)
            {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
            {
                progressBar.setVisibility(View.GONE);
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
            App.initImageLoader(context);
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
