import java.util.List;

record RepositoryPayment(List<Payment> payments) {
    void addPayment(Payment payment) {
        payments.add(payment);
    }

    List<Payment> getAllPayments() {
        return payments;
    }
}

