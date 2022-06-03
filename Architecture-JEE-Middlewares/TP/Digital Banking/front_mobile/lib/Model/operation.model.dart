
 class Operation {
int id;
DateTime operationDate ;
double amount;
 String type;
String description ;

Operation(this.id,this.operationDate,this.amount,this.type,this.description);
factory Operation.fromJson(dynamic json) {
 return Operation(json['id'] as int, json['operationDate'] as DateTime,json['amount'] as double,json['type'] as String,json['description'] as String);
}

}

class OperationResponseObject {
String accountId ;
double balance ;
int currentPage;
int totalPages;
int pageSize;
 List<Operation> accountOperationDTOS =[];
 OperationResponseObject(this.accountId,this.balance,this.currentPage,this.totalPages,this.pageSize,this.accountOperationDTOS);

factory OperationResponseObject.fromJson(dynamic json) {
 return OperationResponseObject(json['accountId'] as String, json['balance'] as double,json['currentPage'] as int,json['totalPages'] as int,json['pageSize'] as int,
     (json['accountOperationDTOS'] as List<dynamic>).map((e) => Operation.fromJson(e)).toList()
 );
}
}