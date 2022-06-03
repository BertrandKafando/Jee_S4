class AccountDetails {
          String accountId ;
            double balance ;
         int currentPage ;
           int totalPages;
          int pageSize  ;
List< AccountOperationDto> accountOperationDtos=[];
AccountDetails(this.accountId,this.balance,this.currentPage,this.totalPages,this.pageSize);
}

class AccountOperationDto {
 int id;
DateTime operationDate;
double amount;
 String description;
String type;

AccountOperationDto(this.id,this.operationDate,this.amount,this.description,this.type);
}