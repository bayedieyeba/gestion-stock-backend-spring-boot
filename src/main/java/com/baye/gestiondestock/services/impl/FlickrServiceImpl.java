package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
@AllArgsConstructor
public class FlickrServiceImpl implements FlickrService {

    private Flickr flickr;

    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData() ;
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo,uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }


}
