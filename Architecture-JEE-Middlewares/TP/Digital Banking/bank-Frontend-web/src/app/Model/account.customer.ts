import {Customer} from "./customers.model";

export interface Account {
  type:          string;
  id:            string;
  balance:       number;
  createAt:      Date;
  status:        null;
  customer:      Customer;
  overDraft?:    number;
  interestRate?: number;
}
