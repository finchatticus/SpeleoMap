package ua.kpi.speleo.app.graphics;

public class ConnectModel {
    private Model model1;
    private Model model2;

    public ConnectModel() {

    }

    public ConnectModel(Model model1, Model model2) {
        this.model1 = model1;
        this.model2 = model2;
    }

    public Model getModel1() {
        return model1;
    }

    public void setModel1(Model model1) {
        this.model1 = model1;
    }

    public Model getModel2() {
        return model2;
    }

    public void setModel2(Model model2) {
        this.model2 = model2;
    }
}
