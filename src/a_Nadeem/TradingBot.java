package a_Nadeem;


	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.OutputStream;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.util.Timer;
	import java.util.TimerTask;

	public class TradingBot {

	//    private static String authToken; // Variable to store the authorization token

		static String authToken = "NFLNXQJMFWEQDIXBPXJQ5DIUD5PDIWXB";
		
	    public static void main(String[] args) {
	        // Step 1: Fetch token for authorization
//	    	authToken = fetchAuthToken();
	        if (authToken == null) {
	            System.out.println("Failed to fetch authorization token. Exiting...");
	            return;
	        }

	        // User-defined inputs
	        String script = "AAPL"; // Example script (can be replaced by user input)
	        double targetPrice = 150.0; // User-defined buy price
	        int quantity = 10; // User-defined quantity

	        // Step 2: Run a loop every 5 minutes to fetch the current market price
	        Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                double currentMarketPrice = fetchCurrentMarketPrice(script);

	                if (currentMarketPrice > 0) {
	                    System.out.println("Current Market Price: " + currentMarketPrice);

	                    // Step 3: Place buy order if the target price is met
	                    if (currentMarketPrice <= targetPrice) {
	                        System.out.println("Placing buy order...");
	                        boolean isBuyOrderPlaced = placeBuyOrder(script, quantity, currentMarketPrice);
	                        if (isBuyOrderPlaced) {
	                            System.out.println("Buy order placed successfully.");
	                            // Cancel this timer and start the square-off process
	                            timer.cancel();
	                            monitorPriceForSquareOff(script, currentMarketPrice);
	                        }
	                    }
	                }
	            }
	        }, 0, 5 * 60 * 1000); // Executes every 5 minutes
	    }

	    // Method to fetch authorization token
		/*
		 * private static String fetchAuthToken() { try { URL url = new
		 * URL("https://api.example.com/auth/token"); HttpURLConnection connection =
		 * (HttpURLConnection) url.openConnection();
		 * connection.setRequestMethod("POST");
		 * connection.setRequestProperty("Content-Type", "application/json");
		 * 
		 * String payload =
		 * "{\"username\":\"yourUsername\", \"password\":\"yourPassword\"}";
		 * connection.setDoOutput(true); OutputStream os = connection.getOutputStream();
		 * os.write(payload.getBytes()); os.flush();
		 * 
		 * if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
		 * BufferedReader in = new BufferedReader(new
		 * InputStreamReader(connection.getInputStream())); StringBuilder response = new
		 * StringBuilder(); String inputLine;
		 * 
		 * while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
		 * in.close(); // Parse and return the token return
		 * parseTokenFromResponse(response.toString()); } } catch (Exception e) {
		 * e.printStackTrace(); } return null; }
		 */
	    // Method to fetch the current market price
	    private static double fetchCurrentMarketPrice(String script) {
	        try {
	            URL url = new URL("https://api.example.com/marketprice?script=" + script);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.setRequestProperty("Authorization", "Bearer " + authToken);

	            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                StringBuilder response = new StringBuilder();
	                String inputLine;

	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                in.close();
	                // Parse and return the current market price
	                return parsePriceFromResponse(response.toString());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return -1;
	    }

	    // Method to place a buy order
	    private static boolean placeBuyOrder(String script, int quantity, double price) {
	        try {
	            URL url = new URL("https://api.example.com/order/buy");
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Authorization", "Bearer " + authToken);
	            connection.setRequestProperty("Content-Type", "application/json");

	            String payload = String.format(
	                "{\"script\":\"%s\", \"quantity\":%d, \"price\":%.2f}",
	                script, quantity, price
	            );
	            connection.setDoOutput(true);
	            OutputStream os = connection.getOutputStream();
	            os.write(payload.getBytes());
	            os.flush();

	            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    // Monitor price for square-off order
	    private static void monitorPriceForSquareOff(String script, double triggerPrice) {
	        double targetPrice = triggerPrice * 1.02; // 2% above trigger price
	        double stopLossPrice = triggerPrice * 0.99; // 1% below trigger price

	        Timer squareOffTimer = new Timer();
	        squareOffTimer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                double currentMarketPrice = fetchCurrentMarketPrice(script);

	                if (currentMarketPrice > 0) {
	                    System.out.println("Monitoring for square-off...");
	                    System.out.println("Current Market Price: " + currentMarketPrice);

	                    if (currentMarketPrice >= targetPrice) {
	                        System.out.println("Target price reached. Placing square-off order...");
	                        placeSquareOffOrder(script, "sell", currentMarketPrice);
	                        squareOffTimer.cancel();
	                    } else if (currentMarketPrice <= stopLossPrice) {
	                        System.out.println("Stop-loss price reached. Placing square-off order...");
	                        placeSquareOffOrder(script, "sell", currentMarketPrice);
	                        squareOffTimer.cancel();
	                    }
	                }
	            }
	        }, 0, 5 * 60 * 1000); // Executes every 5 minutes
	    }

	    // Method to place square-off order
	    private static boolean placeSquareOffOrder(String script, String orderType, double price) {
	        try {
	            URL url = new URL("https://api.example.com/order/squareoff");
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Authorization", "Bearer " + authToken);
	            connection.setRequestProperty("Content-Type", "application/json");

	            String payload = String.format(
	                "{\"script\":\"%s\", \"orderType\":\"%s\", \"price\":%.2f}",
	                script, orderType, price
	            );
	            connection.setDoOutput(true);
	            OutputStream os = connection.getOutputStream();
	            os.write(payload.getBytes());
	            os.flush();

	            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    // Helper method to parse token from the API response
		/*
		 * private static String parseTokenFromResponse(String response) {
		 */	        // Parse and return the token (implement JSON parsing here)
		/*
		 * return response; // Replace with actual parsing logic }
		 */
	    // Helper method to parse price from the API response
	    private static double parsePriceFromResponse(String response) {
	        // Parse and return the market price (implement JSON parsing here)
	        return Double.parseDouble(response); // Replace with actual parsing logic
	    }
	}

	  
	
	

