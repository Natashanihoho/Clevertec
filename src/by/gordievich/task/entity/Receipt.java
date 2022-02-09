package by.gordievich.task.entity;


public class Receipt {

    private StringBuilder stringBuilder = new StringBuilder();

    public Receipt() {
        initialization();
    }

    private void initialization() {
        stringBuilder.append(String.format("ID | DESCRIPTION | PRICE | NUMBER | TOTAL\n"));
        stringBuilder.append(String.format("-----------------------------------------\n"));
    }

    public void addPosition(Position position) {
        stringBuilder.append(position.toString());
        stringBuilder.append("\n");
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }
}
