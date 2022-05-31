import 'package:flutter/material.dart';

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
        BlocProvider(create: (context)=>ContactBloc(new ContactState(requested: Requested.NONE, contacts: [], errorMessage: ''), new ContactRepository())),
        BlocProvider(create: (context)=>MessageBloc(initialState: new MessageState(messages: [],currentEvent: ContactMessage(contact: Contact())  ), messageRepository: new MessageRepository()),)
      ],
      child: MaterialApp(
        theme: ThemeData(
            primarySwatch: Colors.deepOrange
        ),
        routes: {
          "/":(context)=>Home(),
          "/contacts": (context) => ContactPage(),
          "/message":(context)=>MessagePage(),
        },
      ),

    );
  }
}
