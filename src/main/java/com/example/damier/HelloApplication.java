package com.example.damier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

public class HelloApplication extends Application {

    private int coordXperso = 0;
    private int coordYperso = 0;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    
    private GridPane gp_commandes;
    private GridPane gp_game;

    private Button bt_avancer;

    private int[][] lePlateau = new int[8][8];
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        gp_commandes = new GridPane();
        gp_commandes.setHgap(15);
        gp_commandes.setVgap(15);
        gp_game = new GridPane();

        // initialisation du tableau de plateau de jeu
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                this.lePlateau[i][j] = 0;
            }
        }
        // position initiale de Mario
        lePlateau[coordXperso][coordYperso] = 1;

        Text unTexte = new Text("Bienvenue");
        gp_commandes.add(unTexte, 0, 0);

        Button bt_debut = new Button("Commencer");
        bt_debut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                bt_avancer.setVisible(true);
                loadGameUI();

            }
        });

        bt_avancer = new Button("Avancer");
        bt_avancer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                avancerPerso();
            }
        });

        gp_commandes.add(bt_debut,1,0);
        gp_commandes.add(bt_avancer,2,0);
        bt_avancer.setVisible(false);
        gp_commandes.add(gp_game,0,1, 8, 1);

        Scene scene = new Scene(gp_commandes, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    private void avancerPerso() {
        if(coordXperso%2 == 0) {
            if (coordYperso < 7) {
                coordYperso = coordYperso + 1;
            } else {
                coordXperso = coordXperso + 1;
                coordYperso = 7;
            }
        }else{
            if (coordYperso > 0) {
                coordYperso = coordYperso -1 ;
            }else{
                coordXperso = coordXperso + 1;
                coordYperso = 0;
            }
        }
        /*
        coordXperso = coordXperso +1;
        coordYperso = coordYperso +1;
        */
        lePlateau[coordXperso][coordYperso] = 1;
        loadGameUI();
    }

    private void loadGameUI() {
        gp_game.getChildren().clear();

        Class<?> clazz = this.getClass();

        InputStream input = clazz.getResourceAsStream("/com/example/damier/mario.png");

        Image image = new Image(input);

        // image décor vide
        InputStream inputDecor = clazz.getResourceAsStream("/com/example/damier/decor.png");
        Image imageDecor = new Image(inputDecor);

        // Ajout des cases noires et blanches au damier
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if(lePlateau[row][col] == 0){
                    ImageView imageViewDecor = new ImageView(imageDecor);
                    imageViewDecor.setFitHeight((WIDTH-100)/8);
                    imageViewDecor.setFitWidth((HEIGHT-100)/8);
                    gp_game.add(imageViewDecor, col, row);
                }else{
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight((WIDTH-100)/8);
                    imageView.setFitWidth((HEIGHT-100)/8);

                    gp_game.add(imageView, col, row);
                }
                /*
                Rectangle rect = new Rectangle((WIDTH-100) / 8, (HEIGHT-100) / 8);
                if ((row + col) % 2 == 0) {
                    rect.setFill(Color.BLACK);
                } else {
                    rect.setFill(Color.WHITE);
                }
                gp_game.add(rect, col, row);
                */

            }
        }

        // Ajout des coordonnées aux cases
        int numero_case = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                numero_case = numero_case + 1;
                Text text = new Text("" + numero_case);
                if(row%2 == 1) {
                    gp_game.add(text, 7-col, row);
                }else{
                    gp_game.add(text, col, row);
                }
            }
        }



    }
}
