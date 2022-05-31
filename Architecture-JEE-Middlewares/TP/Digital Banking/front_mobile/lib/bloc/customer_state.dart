part of 'customer_bloc.dart';

enum  Requested{Loaded,Loading,Error,NONE}
 class CustomerState {
  List<Customer> customers;
  Requested requested;
  String errorMessage;
 CustomerState({required this.requested, required this.customers,required this.errorMessage});
}

class CustomerInitial extends CustomerState {
  CustomerInitial({required Requested requested, required List<Customer> customers, required String errorMessage}) : super(requested: requested, customers: customers, errorMessage: errorMessage);
}
