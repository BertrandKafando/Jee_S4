import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:front_mobile/Repositories/customerRep.dart';
import 'package:front_mobile/UI/pages/Customer_page.dart';
import 'package:front_mobile/bloc/customer_bloc.dart';

import 'UI/pages/home_page.dart';
void main() {
  runApp(const MyApp());
}


class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return  MultiBlocProvider(
      providers: [
        BlocProvider(create: (context)=>CustomerBloc(new CustomerState(requested: Requested.NONE, customers: [], errorMessage: ''), new CustomerRepository())),

      ],
      child: MaterialApp(
        theme: ThemeData(
            primarySwatch: Colors.deepOrange
        ),
        routes: {
          "/":(context)=>Home(),
          "/customers": (context) => CustomerPage(),
        },
        initialRoute: "/customers",
      ),

    );
  }
}
