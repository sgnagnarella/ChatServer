/**
 * Copyright 2011 Google
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.appengine.codelab;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * FriendStore stores the logged in users 
 * 
 * @author 
 *
 */
public class FriendStore {
  private Set<String> friendsList = new HashSet<String>();
  
  //Will store allowed friends per user comming from salesforce
  private Map<String,HashSet<String>> userAllowedFriends = new HashMap<String,HashSet<String>>();
  
  private static FriendStore instance ;
  private static final Logger logger = Logger.getLogger(FriendServlet.class.getCanonicalName());
	
	/**
	 * Dummy constructor for Singleton Class implementation
	 */
  private FriendStore(){
	  HashSet<String> pepeAllowed = new HashSet<String>();
	  pepeAllowed.add("Seba");


	  HashSet<String> sebaAllowed = new HashSet<String>();
	  sebaAllowed.add("Pepe");

	  HashSet<String> nicoAllowed = new HashSet<String>();
	  nicoAllowed.add("Seba");
	  nicoAllowed.add("Pepe");

	  
	  userAllowedFriends.put("Nico", nicoAllowed);
	  userAllowedFriends.put("Pepe", pepeAllowed);
	  userAllowedFriends.put("Seba", sebaAllowed);
  }
	
	/**
	 * Gives the singleton object of FriendStore class 
	 * 
	 * @return FriendStore object
	 */
  public static FriendStore getInstance(){
    if(instance==null)
      instance = new FriendStore();
    return instance;
  }
	
	/**
	 * Adds the new user
	 * 
	 * @param user The user to be added in the set 
	 */
  void addNewFriend(String user){
    logger.log(Level.INFO,"User {0} is added to the list",user);
    friendsList.add(user);
  }
	
	/**
	 * Removes a new user from the set 
	 * 
	 * @param user The user that needs to be removed from the set
	 */
  void removeFriend(String user){
    logger.log(Level.INFO,"User {0} is removed from the list",user);
    friendsList.remove(user);
  }
	
	/**
	 * Gives the complete set of users sorted  
	 * 
	 * @return The TreeSet object of users (String)
	 */
  Set<String> getFriends(){
    logger.log(Level.INFO,"Users sorted and the set returned");
    return new TreeSet<String>(friendsList);
  }
  
	/**
	 * Gives the list of allowed connections for a particular user
	 * 
	 * @return The TreeSet object of users (String)
	 */
	Set<String> getFriends(String user){
	  logger.log(Level.INFO,"Users sorted and the set returned");
	  
	  Set<String> allowedFriendsList = new HashSet<String>();
	  
	  Iterator<String> onlineFriends = friendsList.iterator();
	  
	  Set<String> userFriendsWhiteList = userAllowedFriends.get(user);
	  
	  if(userFriendsWhiteList.size()>0){
		  while(onlineFriends.hasNext()){
			  String friend = onlineFriends.next(); 
			  if (userFriendsWhiteList.contains(friend)){
				  allowedFriendsList.add(friend);
			  }
		  }
	  }

	  
	  return new TreeSet<String>(allowedFriendsList);
	}
	
	/**
	 * Gives the list of user who can see this user
	 * 
	 * @return The TreeSet object of users (String)
	 */
	Set<String> getFriendsWhoCanSeeUser(String user){
	  logger.log(Level.INFO,"Users sorted and the set returned");
	  
	  Set<String> allowedFriendsList = new HashSet<String>();
	  
	  Iterator<String> onlineFriends = friendsList.iterator();
	  
	  
	  

	  while(onlineFriends.hasNext()){
		  String friend = onlineFriends.next(); 
		  Set<String> userFriendsWhiteList = userAllowedFriends.get(friend);
		  if (userFriendsWhiteList.contains(user)){
			  allowedFriendsList.add(friend);
		  }
	  }
  

	  
	  return new TreeSet<String>(allowedFriendsList);
	}
}
