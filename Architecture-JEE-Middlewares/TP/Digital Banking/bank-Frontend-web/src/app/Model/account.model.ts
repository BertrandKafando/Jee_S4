export interface AccountDetails {
  accountId:            string;
  balance:              number;
  currentPage:          number;
  totalPages:           number;
  pageSize:             number;
  accountOperationDtos: AccountOperationDto[];
}

export interface AccountOperationDto {
  id:            number;
  operationDate: Date;
  amount:        number;
  description:   string;
  type:          string;
}
