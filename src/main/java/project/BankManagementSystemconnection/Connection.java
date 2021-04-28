package project.BankManagementSystemconnection;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Connection {
	
	private static Firestore db;
	
	public static Firestore getConnection() throws IOException {
		if(db==null) {
			
			String exampleString="{\r\n"
					+ "  \"type\": \"service_account\",\r\n"
					+ "  \"project_id\": \"bms000\",\r\n"
					+ "  \"private_key_id\": \"92ee56a4a56651e43b413ddb6881c976d1b29e68\",\r\n"
					+ "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCpLJ4Yh/9VqSN+\\n5xZtdMI3fDIlpcExj/p6azczitcU2BjfP2Lr4OhSkKbDQvXlIQ3Ns98gC9Crxktp\\nwdZOOby83RQwdvLnGPzVZWEUt4ovp+Lhh7FYuN9yWGIDnI7suzbfBcztuQimPrjM\\nsTc3GnP6h4wSt+RDg10OVWFOeqmERcp8fsm8/NZVbEIbH0W0hwyZur70BoEdKc/D\\nWEEReA8ANHDvzvqOY79HotakT7Gg8YvZ7dBd3UnRK5APEW1xkih/q/s52fs617Bw\\ns8NKi7YMO0hy0oaVmPn47q27Nqz0/nnBcu47vzW5E7jZrvnqzPiGi5L3lcC45CmW\\nUFejbMwlAgMBAAECggEAFwSYGjRe3HPf/YNJvOjXCB+g3xh3edviM8fkweGRNCfW\\n8oE4X2nBkoJV/lldBakCrMtdf4m8AbNATYpvM0ON15CpD5MPp7vxosNheyUh6ywH\\npbIFwlGdLXyzEVS66aDrbVrnsCyt7Zi5Iji2j4vM3fiVaXKV302/WpTpYXk1GWcA\\n2X9U8+oruWmDvmgg+kAIDBsT/G1ItZoY22vKFmLSnFl6NtH1Yn+1sNFz04e27+Nz\\ni30nkY/XjSZguyeqEjVJ41ra7jnbh/kjbpTtMmwPabs0wOmJwRgEqv5r6J0QVw7+\\nM0qPtcbdw5BnbZDFRoYkjzwbMMJrbM4WPo/BOUIYQQKBgQDbTfUKqWBg+fTFJXY/\\nGHRtO01JQrLu0hSiGoqhlNw6TVB6ShRW77V7Ba/xXJXm6NBGyvUZMy19ugptZHXO\\nKi3nmBTMF+JMqMGTqAIveSNm3T4BBXxMQluRlL9ji+PAkMPZbbBCpU4qOS1XHOKw\\nHn+ONjLPmjKJzKwPF01uraFakQKBgQDFe00g2mPiCJrpKrfxjg/5BhXzkWw6j6yx\\nP11MSE2ZLf43WXGY1zwzEwiq24FWDnKIeCuzjrn8WctTtxXF+4gUcb8jnjLFvmxB\\n063SpwZ5GuAKA8PDVgf8ndvkcDx5YQEHNnvfkgD6NE3DjPZ3d0hNep3Ekw5m+bn7\\nh2xWHLcaVQKBgQDAi12NktBv1IW3YgDDvPDbxqlAnTMEw6Yh/TOiUlRZS/BQriHV\\ngnjQHlovi56BMf9LaH5xNaHHNURab02PSTEsmTYJSx8BN1leDrB8hdszQSRjOTG8\\nsrY6x2KSQAwi0fdiOw/6M7DBGZpZVZ8iCmTrs2JBK50bp3uZdFgjyIZWQQKBgCm8\\nrIlj/rY38V2UPLodtzx/T8ZhlsktsmbnqHVF1rioItFZ4wtVehClVjCeIdCpTwWO\\nuFXDtYUjCTkROdXVGmaPv8DsX3ixjTnAXj1mNZUE3BLhxs8S8K8wQdge7HkOymPW\\n3QlcVz6KLWFF+pU3LZ3Ka2uvVNzorKj/byMoAH7dAoGAYS/2/9Aewg6+QzbqhsrB\\ndKeSMn9YM2NItvrKahQwmsNuQZ72jrqvi1ZX7O3RF+J4b4Ca82AxObkTbYUpluLC\\nVRq84ZvtXChj1VL2/0w8Y+K95pFWOeqifZKEvXRSBaXUpbjw9bpObT1CVx/wHgeC\\nMi6uh/vlXYw/Emn0hlQtz+E=\\n-----END PRIVATE KEY-----\\n\",\r\n"
					+ "  \"client_email\": \"firebase-adminsdk-3vmq4@bms000.iam.gserviceaccount.com\",\r\n"
					+ "  \"client_id\": \"115252634695820583871\",\r\n"
					+ "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n"
					+ "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n"
					+ "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n"
					+ "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-3vmq4%40bms000.iam.gserviceaccount.com\"\r\n"
					+ "}\r\n"
					+ "";
			InputStream serviceAccount = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8)); //new FileInputStream("./bms000-92ee56a4a566.json");
	    	GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
	    	FirebaseOptions options = new FirebaseOptions.Builder()
	    	    .setCredentials(credentials)
	    	    .build();
	    	FirebaseApp.initializeApp(options);

	    	db = FirestoreClient.getFirestore();
		}
		return db;
	}
}
