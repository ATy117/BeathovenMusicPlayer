package controller;

import FileUploaderService.FileUploader;
import FileUploaderService.PhotoUploader;

import java.io.File;

public abstract class UserRegisterController extends Controller{

	public abstract boolean registerUser(String username, String password, String firstname, String lastname, File file);

	public abstract void backPane ();

	public File getPhotoFile() {
		FileUploader uploader = new PhotoUploader(primaryStage);
		return uploader.getUploadedFile();
	}
}
