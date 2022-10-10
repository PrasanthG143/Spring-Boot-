package com.example.controller;

import com.example.common.AddressByLatLng;
import com.example.common.SMS;
import com.example.dao.mailerTest;
import com.example.model.Billupload;
import com.example.model.Groups;
import com.example.model.Newtrip;
import com.example.model.User;
import com.example.model.UserProfile;
import com.example.service.BilluploadService;
import com.example.service.UserProfileService;
import com.example.service.UserService;
import com.example.service.newtripService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@MultipartConfig
@Controller
@SessionAttributes("roles")

public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	newtripService newService;

	@Autowired
	BilluploadService billService;

//	@Autowired
	//private MailSender crunchifymail;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	com.example.service.reportService reportService;
	
	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		System.out.println("Login Page");
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/home";
		}
	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 * RememberMe functionality is useless in your app.
	 */
	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userslist";
	}
	 @RequestMapping(value={"/book"},method=RequestMethod.GET)
	   public ResponseEntity<List<User>> list() {
	      List<User> books = userService.findAllUsers();
	      return ResponseEntity.ok().body(books);
	}


	
	

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String Home(ModelMap model) {
		SMS smssend = new SMS();
		httpSession.setAttribute("userid",getPrincipal());
		List<User> users = userService.findAllUsers();
		model.addAttribute("loggedinuser", getPrincipal());
		String toAddress = "prasanth@glovision.co";
		String fromAddress = "prasanthmpv@gmail.com";
		System.out.println("List 1==="+reportService.getDashboard());
		System.out.println("BILSS====="+reportService.getCategoryWise());
		model.addAttribute("dashboard",reportService.getDashboard());
		model.addAttribute("category",reportService.getCategoryWise());
		// email subject
		String subject = "Hey.. This email sent by Prasanth Spring MVC Tutorial";

		// email body
		String body = "There you go.. You got an email.. Let's understand details on how Spring MVC works -- By Crunchify Admin";

		System.out.println("to" + toAddress + "from" + fromAddress + "sub" + subject + "BODY" + body);
		// smssend.Sms();//Sms Sendng
		//this.Email(fromAddress, toAddress, subject, body);// Email Sending

		return "home";
	}

	
	
	// http://localhost:port/rest/api/management 
	
	// with methid name : get 
	// if nayparam then param 
	
	// this is how happens when any external language is trying to  access ur service 
	
	//main purpose of request mapping serves there 
	
	@RequestMapping(value = { "/management" }, method = RequestMethod.GET)
	public String Usermanagement(ModelMap model){
		Groups group=new Groups();
		model.addAttribute("usermanagement", group);
		List<Groups> groups= userProfileService.findGroups();
		model.addAttribute("groups",groups);
	
		
		return "roles";
	}
	@RequestMapping(value = { "/management" }, method = RequestMethod.POST)
	public String Group(@ModelAttribute("usermanagement") @Valid Groups group,BindingResult result,ModelMap model){
		System.out.println("group is" + group + "-p-q" + result);
		List<Groups> groups= userProfileService.findGroups();
		 model.addAttribute("groups",groups);
	
       
		if (result.hasErrors()) {
			return "roles";
		}
		if (!userProfileService.isSSOUnique( group.getGroupName())) {
			FieldError ssoError = new FieldError("groups", "groupName", messageSource.getMessage("NotEmpty.groups.groupName",
					new String[] { group.getGroupName() }, Locale.getDefault()));
			result.addError(ssoError);
			System.out.println("Error IS " + ssoError);
			return "roles";
		}
		
		userProfileService.saveGroup(group);
		model.addAttribute("success",
				"usermanagement" + group.getGroupName()+ " registered successfully");
		

		return "roles";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		System.out.println("=================" + user);
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
	httpSession.setAttribute("users",userService.findAllUsers());
	httpSession.setAttribute("groups",userProfileService.findGroups());


		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
		System.out.println("user is" + user + "-p-q" + result);

		if (result.hasErrors()) {
			return "registration";
		}
		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			System.out.println("Error IS " + ssoError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "registrationsuccess";
	}

	

	@ModelAttribute("users")
	public List<User> allusers() {
		return userService.findAllUsers();
	}

	
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in
		 * UI which is a unique key to a User.
		 * if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
		 * FieldError ssoError =new
		 * FieldError("user","ssoId",messageSource.getMessage(
		 * "non.unique.ssoId", new String[]{user.getSsoId()},
		 * Locale.getDefault())); result.addError(ssoError); return
		 * "registration"; }
		 */
		userService.updateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@RequestMapping(value = { "/newtrip" }, method = RequestMethod.GET)
	public String newtrip(ModelMap model) {
		Newtrip newtrip = new Newtrip();
		model.addAttribute("newtrip", newtrip);
		model.addAttribute("loggedinuser", getPrincipal());
		return "forms";
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/newtrip" }, method = RequestMethod.POST)
	public String savetrip(@Valid Newtrip newtrip,BindingResult result, ModelMap model,MultipartHttpServletRequest request) throws IOException, ServletException {
		System.out.println("HELLO" + result.hasErrors());
		//Iterator<String> itr = newtrip.getFileNames();

	//	System.out.println("FILES============" + itr.toString());
		
		String path1="noimage";
		String photo = "";
		String path = "../../TripImages";

		
		if (result.hasErrors()) {
			return "forms";
		}
		
			MultipartFile file = newtrip.getPath1();
			// MultipartFile mpf = request.getFile(itr.next());
			Part filePart = request.getPart("file");
		    
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
			//	String rootPath = "/home/glodeveloper/Desktop/";
				File dir = new File(path + File.separator + "TripImages");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				path1=serverFile.getName();
			
			} catch (Exception e) {
				
			}
		} else {
			path1="noimage";
		
		}
	
		newtrip.setPath(path1);
		newService.Savetrip(newtrip);
		model.addAttribute("success",
				"newtrip " + newtrip.getUserid() + " " + newtrip.getTripname() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "forms";
	}
	
	
	
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
			//	String rootPath = "/home/glodeveloper/Desktop/";
				String path = "../../TripImages";
				
				File dir = new File(path + File.separator + "BillImages");
				System.out.println(dir);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				//logger.info("Server File Location="
						//+ serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}


	@RequestMapping(value = { "/edit-trips-{id}" }, method = RequestMethod.GET)
	public String editTrip(@PathVariable String id, ModelMap model) {
		Newtrip newtrip = newService.findById(id);
		model.addAttribute("newtrip", newtrip);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "forms";
	}

	@RequestMapping(value = { "/edit-trips-{id}" }, method = RequestMethod.POST)
	public String updateTrip(@ModelAttribute("newtrip")@Valid Newtrip newtrip, BindingResult result, ModelMap model, @PathVariable int id) {

		if (result.hasErrors()) {
			return "forms";
		} 
		newService.updateTrip(newtrip);

		model.addAttribute("success",
				"newtrip " + newtrip.getUserid() + " " + newtrip.getTripname() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "forms";
	}

	@RequestMapping(value = { "/trips" }, method = RequestMethod.GET)
	public String listTrips(ModelMap model) {

		List<Newtrip> Newtrip = newService.findByUserId(getPrincipal());
		model.addAttribute("trips", Newtrip);
		model.addAttribute("loggedinuser", getPrincipal());
		return "totaltrips";
		
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = {
			"/db_fetch" }, method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	@ResponseBody
	public List<Newtrip> findTripByTripunique(@RequestParam("Trip") String trip, HttpServletRequest request,
			HttpServletResponse response) {
		// Employee add(HttpServletRequest request, HttpServletResponse
		// response)
		// throws E
		response.setHeader("Access-Control-Allow-Origin", "*");

		System.out.println(trip + "REACHED++++++++++++++++++++++++++++====" + newService.findTripByTripunique(trip));
		List<Newtrip> trips = newService.findTripByTripunique(trip);
		;

		return trips;
	}

	@RequestMapping(value = { "/billupload" }, method = RequestMethod.GET)
	public String Billupload(ModelMap model) {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("newtrip", newService.findByUserId(getPrincipal()));

		return "BillUpload";
	}

	// private static String UPLOADED_FOLDER = "var/www/html/TemBillimages";
	@CrossOrigin(origins = "*")
	@RequestMapping(value = { "/savebillupload" }, method = RequestMethod.POST)
	public @ResponseBody String SaveBill(MultipartHttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String msg = "Successfully uploaded!";
		System.out.println(request.getAttributeNames());

		try {

			Billupload bill = new Billupload();
			Iterator<String> itr = request.getFileNames();

			System.out.println("FILES============" + itr.toString());
			System.out.println(request.getPart("file"));

			String photo = "";
			String path = "G:\\TripImages";

			if (itr.hasNext()) {
				MultipartFile mpf = request.getFile(itr.next());
				// MultipartFile mpf = request.getFile(itr.next());
				Part filePart = request.getPart("file");

				System.out.println("FILE" + filePart);
				try {
					InputStream inputStream = null;
					OutputStream outputStream = null;
					if (mpf.getSize() > 0) {
						System.out.println("FILE" + filePart);
						inputStream = mpf.getInputStream();
						outputStream = new FileOutputStream(path + "/" + mpf.getOriginalFilename());
						System.out.println("====22=========");
						System.out.println(mpf.getOriginalFilename());
						System.out.println("=============");
						bill.setFileName(mpf.getOriginalFilename());// if Image
																	// Present
																	// --
						bill.setType(mpf.getContentType()); // Type of
															// Image-----
						int readBytes = 0;
						byte[] buffer = new byte[8192];
						while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
							System.out.println("===ddd=======");
							outputStream.write(buffer, 0, readBytes);
						}
						outputStream.close();
						inputStream.close();

					} else {
						bill.setFileName("noimage");
						bill.setType("empty");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("s" + e.getMessage());

					bill.setFileName("noimage");
					bill.setType("empty");
				}
			} else {
				bill.setFileName("noimage");
				bill.setType("empty");
				System.out.println("ELSE CONDITION");
			}
			bill.setBillamount(request.getParameter("billamount"));
			bill.setBillpaid(request.getParameter("billpaid"));
			bill.setBilluploaddate(request.getParameter("billuploaddate"));
			bill.setDescription(request.getParameter("description"));

			bill.setLatitude(request.getParameter("latitude"));
			bill.setLongitude(request.getParameter("longitude"));
			bill.setStatus(request.getParameter("status"));
			bill.setMembers(request.getParameter("members"));
			bill.setTripunique(request.getParameter("tripunique"));
			bill.setRepair(request.getParameter("repair"));
			bill.setTripunique(request.getParameter("tripunique"));
			bill.setUploadatonside(request.getParameter("uploadatonside"));
			bill.setUserid(request.getParameter("userid"));
			bill.setSlct(request.getParameter("slct"));
			bill.setDateofjourney(this.date());
			bill.setPermittedamount(0);
			bill.setTax(0);
			bill.setInitialAmount(0);

			bill.setAddress(AddressByLatLng.getAddressByLatLng(request.getParameter("latitude"),
					request.getParameter("longitude")));

			System.out.println("BILLL" + bill);
			Boolean status = billService.saveBill(bill);

			if (status) {
				return msg;
			} else {
				return msg = "Error While uploading!";
			}

		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
			msg = "Error While uploading!";
		}

		return msg;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {

		// 0. notice, we have used MultipartHttpServletRequest
		// 1. get the files from the request object
		Iterator<String> itr = request.getFileNames();
		System.out.println("FILE Name" + request.getParameter("name"));

		if (itr.hasNext()) {

			MultipartFile mpf = request.getFile(itr.next());
			System.out.println(mpf);

			System.out.println(mpf.getOriginalFilename() + " uploaded!");

			try {
				// just temporary save file info into ufile
				// ufile.length = mpf.getBytes().length;
				// ufile.bytes= mpf.getBytes();
				// ufile.type = mpf.getContentType();
				// ufile.name = mpf.getOriginalFilename();
				System.out.println("Size" + mpf.getBytes().length);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("NO IMAGE PRASANTH");
		}

		// 2. send it back to the client as <img> that calls get method
		// we are using getTimeInMillis to avoid server cached image
		return "";

	}

	/**
	 * This method returns true if users is already authenticated [logged-in],
	 * else false.
	
	public void Email(String fromAddress, String toAddress, String subject, String body) {

		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(fromAddress);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(body);
		System.out.println("After Calling: ==" + crunchifyMsg);
		crunchifymail.send(crunchifyMsg);
	} */
	
	
	 public static String date(){
   	  java.util.Date today = new java.util.Date();
         //If you print Date, you will get un formatted output
         System.out.println("Today is : " + today);
         //formatting date in Java using SimpleDateFormat
         SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
         String date = DATE_FORMAT.format(today);
         System.out.println("Today in dd-MM-yyyy format : " + date);
       return date;
   }
	 
	 
	 
	 

	private boolean isCurrentAuthenticationAnonymous() {
		System.out.println("LOGIN CHECK");
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("LOGIN CHECK AFTER"+authentication);
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}

	

