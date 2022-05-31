import 'package:http/http.dart' as http;
import 'package:front_mobile/Model/customer.dart';
import 'dart:convert';
class CustomerRepository{

  Future<List<Customer>> getallCustomers() async{
   // await Future.delayed(Duration(seconds: 1)); //attendre 1 seconde
    List<Customer> customers=[];

    http.get(Uri.parse("localhost:8084/customers"))

        .then((response) {
      customers= json.decode(response.body);
      return customers;

    }).catchError((onError){
      print(onError);
      throw Exception(onError);
    });
  }






}