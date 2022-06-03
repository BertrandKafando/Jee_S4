import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:front_mobile/Model/customer.dart';
import 'package:front_mobile/Repositories/customerRep.dart';
import 'package:meta/meta.dart';

part 'customer_event.dart';
part 'customer_state.dart';

class CustomerBloc extends Bloc<CustomerEvent, CustomerState> {
  CustomerRepository customerRepository;
  CustomerBloc(CustomerState customerState, this.customerRepository) : super(CustomerInitial(customers: [], errorMessage: '', requested: Requested.Loading)) {
    on<CustomerEvent>((event, emit) async {
       if (event is ListCustomerEvent ){
         try {
           emit(CustomerState(
               customers: [], errorMessage: '', requested: Requested.Loading));
           List<Customer>contacts = await customerRepository.getallCustomers();
           print(contacts);
          emit(CustomerState(
               customers: contacts,
               errorMessage: '',
               requested: Requested.Loaded));
         } catch (e) {
           emit(CustomerState(
              customers: [],
               errorMessage: e.toString(),
               requested: Requested.Error));
         }
       }
    });
  }
}
