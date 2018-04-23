# Test Application
# Include libraries 
    
		   compile 'io.reactivex:rxandroid:1.2.1'	 for implementing RX Java			 
			 
		testCompile 'junit:junit:4.12'
    testCompile "org.mockito:mockito-core:2.8.47"

# Add Developer APi key in Common Constants
 public static final String DEVELOPER_API_KEY = "54ea9cc7a6f2472fb7185ec6bf875725";
 
 # Get Develope key in below link
 https://developer.nytimes.com/signup
 
 Application Flow : 
 
 Application support from version 21 onWards
 
 Launch ---> Main Activity--->getting data from api
                 Displayed in list.
								 
						Details Activity--->will show the details of each item in the list.
 
 
