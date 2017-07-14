<?php

// Email id to which data is sent
$email_to = 'vyas.vashisth@gmail.com,sunshine.pari15@gmail.com,aditya.dave2017@gmail.com';

// Subject of the email
$email_subject = 'Rann Utsav';

// Retrieve data from form fields
$select_package = $_REQUEST['select_package'];
$no_of_tents = $_REQUEST['no_of_tents'];
$no_of_passengers = $_REQUEST['no_of_passengers'];
$package_date = $_REQUEST['package_date'];
$tent_type = $_REQUEST['tent_type'];
$email_id = $_REQUEST['email_id'];
$identity_proof = $_REQUEST['identity_proof'];
$id_number = $_REQUEST['id_number'];
$mobile_no = $_REQUEST['mobile_no'];
$arrive_by = $_REQUEST['arrive_by'];
$pickup_point_time = $_REQUEST['pickup_point_time'];
$guest_address = $_REQUEST['guest_address'];
$special_requirement = $_REQUEST['special_requirement'];
$guest_name = $_REQUEST['guest_name'];
$guest_age = $_REQUEST['guest_age'];
$guest_gender = $_REQUEST['guest_gender'];
$is_senior = $_REQUEST['is_senior'] === 'yes' ? 'Yes' : 'No';

// Company's URL and LOGO URL
$company_url = 'http://www.witstyleart.com/demo/rannutsav_web';
$company_logo_url = 'http://www.witstyleart.com/demo/rannutsav_web/img/rann_logo.png';
$greetings_from = 'Rann Utsav';

// Name of the person sending
$from = "Rann Utsav";

// Specify body of the email
$email_body =
    '<html>' .
    '<body>' .
    '<div style="width:930px; height:auto; background:#A60000; padding:5px; overflow:hidden; alignment: center;">' .
    '<div style=" background:#FFF; padding:15px;">' .
    '<div align="left"><img src=' . $company_logo_url . ' width="164" height="75"/></div>' .
    '<div style="clear:both"></div>' .
    '<hr style="border:none; border-bottom:1px dashed #CCC; ">' .
    '<div style="height:20px;"></div>' .
    '<div style=" font-family:Georgia, Times, serif; font-size:13px; color:#333; font-weight:normal; text-align:left;">Dear, <span style="font-weight:bold;color:#A60000; text-align:left;">Sir</span></div>' .
    '<div style="clear:both;"></div>' .
    '<br>' .
    '<div  style="font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; padding-bottom:5px;" align="left">Contact</div>' .
    '<br>' .
    '<div style="background:#FCFCFC; padding:10px; overflow:hidden; border:1px solid #E5E5E5;">' .
    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Selected Package:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $select_package . '</div>' .
    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">No. of tents to be booked:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $no_of_tents . '</div>' .
    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">No. of passengers:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $no_of_passengers . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Required Package Date:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $package_date . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Tent Type:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $tent_type . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Email Id:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $email_id . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Identity Proof Type:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $identity_proof . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Id Number:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $id_number . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Mobile No.:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $mobile_no . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Arrival at Bhuj By:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $arrive_by . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Pickup Point & Time:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $pickup_point_time . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Address of the guest:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $guest_address . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Any message / Special Requirement:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $special_requirement . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Name of the Guest:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $guest_name . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Age:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $guest_age . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Gender:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $guest_gender . '</div>' .

    '<div style="clear:both;"></div>' .
    '<hr style="border:none; border-bottom:1px solid #EAEAEA;">' .
    '<div style="width:30%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; text-align:left;">Is Senior Citizen:</div>' .
    '<div style="width:70%; float:left; font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">' . $is_senior . '</div>';


$email_body .=
    '</div>' .
    '<div style="clear:both;"></div>' .
    '<div style="height:20px;"></div>' .
    '<div  style="font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:bold; color:#606060; padding-bottom:5px; text-align:left;">Best Regards,</div>' .
    '<div style="font-family:Arial, Helvetica, sans-serif; font-size:12px; font-weight:normal; color:#606060; text-align:left;">	<a href="' . $company_url . '" target="_blank"">' . $greetings_from . '</a></div>' .
    '</div>' .
    '</div>' .
    '</body>' .
    '</html>';


$headers = 'MIME-Version: 1.0' . "\r\n";
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
$headers .= 'From:' . $from . "\r\n"; // Sender's Email
$headers .= 'Cc:' . $email_to . "\r\n"; // Carbon copy to Sender

// Send email and test.php for success
if (mail($email_to, $email_subject, $email_body, $headers)) {
    // Success
    header('Location: ' . 'index.html');
    exit;
} else {
    // Failure
}