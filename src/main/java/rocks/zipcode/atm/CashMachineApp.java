package rocks.zipcode.atm;

import com.sun.javafx.webkit.theme.RenderThemeImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    Label labelId = new Label("Id:");
    Label labelAmount = new Label("Amount:");
    Label labelAccount = new Label("Account id:");
    Label labelName = new Label("Name:");
    Label labelEmail = new Label("Email:");
    Label labelBalance = new Label("Balance:");
    private TextField fieldId = new TextField("1000");
    private TextField fieldAmount = new TextField("9");
    private TextField fieldAccount = new TextField();
    private TextField fieldName = new TextField();
    private TextField fieldEmail = new TextField();
    private TextField fieldBalance = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        GridPane grid = new GridPane();
        //VBox vbox = new VBox(10);
        //vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        Button btnLogin = new Button("Log In");
        btnLogin.setOnAction( e -> {

            //int id = Integer.parseInt(fieldId.getText());

            System.out.println( "Button_LogIn - " +  fieldId.getText() );
            //cashMachine.login( Integer.parseInt(fieldId.getText()) );
            ///////cashMachine.login2( Integer.parseInt(fieldId.getText()) );
            //cashMachine.loginEmail( Integer.parseInt(fieldId.getText()) );

            Integer interFace = Integer.parseInt(fieldId.getText().toString());
            String idFromLogin = cashMachine.loginId( interFace );
            String nameFromLogin = cashMachine.loginName( interFace );
            String emailFromLogin = cashMachine.loginEmail( interFace );
            String balanceFromLogin = cashMachine.loginBalance( interFace );


            fieldAccount.setText( idFromLogin );
            fieldName.setText( nameFromLogin );
            fieldEmail.setText( emailFromLogin );
            fieldBalance.setText( balanceFromLogin );

            //////System.out.println( "Button_LogIn - " +  cashMachine.login2( Integer.parseInt(fieldId.getText().toString() ) ) );
            System.out.println( "Button_LogIn - " +  cashMachine.loginEmail( Integer.parseInt(fieldId.getText().toString() ) ));

            //areaInfo.setText( fieldId.getText() );
            //areaInfo.setText(cashMachine.toString());
        });
//
//        Button btnDeposit = new Button("Deposit");
//        btnDeposit.setOnAction(e -> {
//            //System.out.println( "Button_Deposit 1. "  + fieldAmount.getText() );
//            Double amount = Double.parseDouble(fieldAmount.getText());
//
//            cashMachine.deposit(amount);
//
//            //areaInfo.setText(cashMachine.toString());
//        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            Integer interFace = Integer.parseInt(fieldId.getText().toString());
            System.out.println(cashMachine.loginBalance(interFace));
            Double amount = Double.parseDouble(fieldAmount.getText());
            cashMachine.deposit(amount);
            areaInfo.setText("You deposited $"+fieldAmount.getText());
            fieldAmount.clear();
        });



        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(fieldAmount.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });
//
//
        Button btnGetBalance = new Button("Get Balance");
        btnGetBalance.setOnAction( e -> {
            int id = Integer.parseInt(fieldAmount.getText());
            cashMachine.getBalance(id);

            areaInfo.setText(cashMachine.toString());
            System.out.println( "CashMachineApp_btnGetBalance"  );
        });

//        Button btnSubmit = new Button("Set Account ID");
//        btnSubmit.setOnAction(e -> {
//            int id = Integer.parseInt(field.getText());
//            cashMachine.login(id);
//
//            areaInfo.setText(cashMachine.toString());
//        });

        Button btnLogOut = new Button("Log Out");
        btnLogOut.setOnAction(e -> {
            cashMachine.exit();
            System.out.println("\u000C");
            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            System.out.println("Exiting");
            //closeProgram(Stage stage);
        } );

//        FlowPane flowpane = new FlowPane();
////        flowpane.getChildren().add(btnLogin);
////        flowpane.getChildren().add(btnDeposit);
////        flowpane.getChildren().add(btnWithdraw);
////        flowpane.getChildren().add(btnGetBalance);
////        flowpane.getChildren().add(btnLogOut);
////        flowpane.getChildren().add(btnExit);
////        flowpane.getChildren().add(btnSubmit);

        Integer lengthLabel = 60;
        Integer lengthField = 70;
        labelId.setPrefWidth(lengthLabel);
        labelId.setMaxWidth(lengthLabel);
        labelId.setAlignment(Pos.CENTER);
        labelAmount.setPrefWidth(lengthLabel);
        labelAmount.setMaxWidth(lengthLabel);
        labelAmount.setAlignment(Pos.BASELINE_RIGHT);

        fieldId.setPrefWidth(lengthField);
        fieldId.setMaxWidth(lengthField);
        fieldAmount.setPrefWidth(lengthField);
        fieldAmount.setMaxWidth(210);
        areaInfo.setPrefWidth(200);
        labelAccount.setAlignment(Pos.BOTTOM_RIGHT);

        grid.setPadding(new Insets(10,10,10,10));
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(labelId,0,0,1,1);
        GridPane.setConstraints(fieldId,1,0);
        GridPane.setConstraints(labelAmount,2,0);
        GridPane.setConstraints(fieldAmount,3,0,3,1);
        GridPane.setConstraints(btnLogin,0,1);
        GridPane.setConstraints(btnDeposit,1,1);
        GridPane.setConstraints(btnWithdraw,2,1);
        GridPane.setConstraints(btnGetBalance,3,1);
        GridPane.setConstraints(btnLogOut,4,1);
        GridPane.setConstraints(btnExit,5,1);
        GridPane.setConstraints(labelAccount,0,2);
        GridPane.setConstraints(fieldAccount,1,2,5,1);
        GridPane.setConstraints(labelName,0,3);
        GridPane.setConstraints(fieldName,1,3,5,1);
        GridPane.setConstraints(labelEmail,0,4);
        GridPane.setConstraints(fieldEmail,1,4,5,1);
        GridPane.setConstraints(labelBalance,0,5);
        GridPane.setConstraints(fieldBalance,1,5,5,1);
        GridPane.setConstraints(areaInfo,0,6,6,2);
        grid.getChildren().addAll(labelId,fieldId,labelAmount,fieldAmount,btnLogin,btnDeposit,btnWithdraw,
                btnGetBalance,btnLogOut,btnExit,labelAccount,fieldAccount,labelName,fieldName,
                labelEmail,fieldEmail,labelBalance,fieldBalance,areaInfo);


        return grid;
    }

//    public void closeProgram(Stage stage){
//        System.out.println( "Exit success ");
//
//        //stage.close();
//    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene( new Scene(createContent(),500,300) );
        stage.setTitle("Cash Machine");
        stage.setOnCloseRequest(e -> closeProgram(stage) );
        stage.show();
    }

    private void closeProgram(Stage stage) {
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
