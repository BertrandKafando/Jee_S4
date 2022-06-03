class Customer{
int id;
String name;
String email;


Customer(this.id,this.name, this.email);

 factory Customer.fromJson(dynamic json) {
  return Customer(json['id'] as int, json['name'] as String,json['email'] as String);
 }
 

}
