package de.florianmarsch.test.play_store_analytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(
				"de/florianmarsch/test/play_store_analytics/app-store-connect-240715-b81281ef35fb.json");

		// https://github.com/googleapis/google-cloud-java#authentication
		Storage storage = StorageOptions.newBuilder().setCredentials(ServiceAccountCredentials.fromStream(inputStream))
				.build().getService();

		Bucket bucket = storage.get("pubsite_prod_rev_09013922825913595048");
		
		
		Blob blob = bucket.get("stats/installs/installs_de.cat_ventures.app_201510_overview.csv");
		System.out.println(new String(blob.getContent()));
		
		
		blob = bucket.get("reviews/reviews_de.cat_ventures.app_201510.csv");
		System.out.println(new String(blob.getContent()));
	}


}
