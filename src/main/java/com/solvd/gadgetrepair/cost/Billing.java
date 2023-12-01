package com.solvd.gadgetrepair.cost;

import com.solvd.gadgetrepair.devices.Gadget;
import com.solvd.gadgetrepair.devices.RepairService;
import com.solvd.gadgetrepair.devices.ServiceRecord;
import com.solvd.gadgetrepair.exceptions.PaymentException;
import com.solvd.gadgetrepair.human.Customer;

import java.time.LocalDateTime;

// Calculates total cost, generates invoices, and processes payments
public abstract class Billing {
    private final AcceptedPayments paymentMethod;

    public Billing(AcceptedPayments paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public AcceptedPayments getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(AcceptedPayments paymentMethod) {
        if (!isPaymentAccepted(paymentMethod)) {
            throw new PaymentException("Unaccepted payment method: " + paymentMethod);
        }
    }

    public void addToRecord(Customer customer, ServiceRecord[] repairRecords, Gadget gadget, RepairService repairService) {
        String currentDate = LocalDateTime.now().toString();
        double repairCost = repairService.calculateRepairCost();
        String problemDescription = gadget.getProblemDescription();

        if (repairRecords.length > 0) {
            ServiceRecord<String> record = new ServiceRecord<>(currentDate, repairCost, problemDescription);
            customer.addRepairRecord(record);
        }
    }

    public void processPayment (Customer customer, RepairService repairService) throws PaymentException {
        AcceptedPayments paymentMethod = getPaymentMethod();
        if (isPaymentAccepted(paymentMethod)) {
            throw new PaymentException("Unaccepted payment method: " + paymentMethod);
        }
    }

    private boolean isPaymentAccepted(AcceptedPayments acceptedMethod) {
        AcceptedPayments[] acceptedMethods = AcceptedPayments.values();
        for (AcceptedPayments payment : acceptedMethods) {
            if (payment == acceptedMethod) {
                return true;
            }
        }
        return false;
    }
}
