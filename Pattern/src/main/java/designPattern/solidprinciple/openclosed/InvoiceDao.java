package designPattern.solidprinciple.openclosed;


import designPattern.solidprinciple.Invoice;

public interface InvoiceDao {
    public void save(Invoice invoice);
}
