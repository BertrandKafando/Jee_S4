import 'package:http/http.dart' as http;
import 'package:front_mobile/Model/customer.dart';
import 'dart:convert';
class CustomerRepository{
/*
  Future<List<Customer>> getallCustomers() async{
    await Future.delayed(Duration(seconds: 1)); //attendre 1 seconde
    List<Customer> customers=[];
try{
  await  http.get(Uri.parse("http://10.0.2.2:8084/customers"))

      .then((response) {
    print(json.decode(response.body));
    //customers= (json.decode(response.body) as List<dynamic>).map((item)=> item as Customer).toList();
    final customers = List<dynamic>.from(
      json.decode(response.body).map<dynamic>(
            (dynamic item) => item,
      ),
    );
    List<Customer> cut=customers.map((e) => Customer.fromJson(e)).toList();
    print(customers);
    print(cut);
    print('no');
    return cut;
      })
      .catchError((onError){
    print(onError);
    throw Exception(onError);
  });
}catch(e){
print(e);
rethrow;
}
    print('ok');
return customers;
  }
*/

  Future<List<Customer>>  getallCustomers() async {
    final response=await  http.get(Uri.parse("http://10.0.2.2:8085/customers"));
    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      final customers = List<dynamic>.from(
        json.decode(response.body).map<dynamic>(
              (dynamic item) => item,
        ),
      );
      List<Customer> cut=customers.map((e) => Customer.fromJson(e)).toList();
      print(customers);
      print(cut);
      print('no');
      return cut;
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      throw Exception('Failed to load album');
    }
    }






}