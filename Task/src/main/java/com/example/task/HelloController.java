package com.example.task;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
public class HelloController implements Initializable {
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private Media media;
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider volume;
    @FXML
    private Slider seekSlider;
    private boolean atEndOfVideo;
    private boolean isPlaying = false;

    @FXML
    private void playVideo(ActionEvent event) {
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

        volume.setValue(mediaPlayer.getVolume()*100);
        volume.valueProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue()/100);
            }
        });
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                if (!seekSlider.isValueChanging()) {
                    seekSlider.setValue(t1.toSeconds());
                }
            }
        });
        seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(atEndOfVideo){
                    seekSlider.setValue(0);
                    atEndOfVideo=false;
                }
                mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
            }
        });
        mediaPlayer.play();
    }
    @FXML
    private void pauseVideo(ActionEvent event){
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

        volume.setValue(mediaPlayer.getVolume()*100);
        volume.valueProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue()/100);
            }
        });
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                if (!seekSlider.isValueChanging()) {
                    seekSlider.setValue(t1.toSeconds());
                }
            }
        });
        seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
            }
        });
        mediaPlayer.pause();
    }
    @FXML
    public void stopVideo(ActionEvent actionEvent) {
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

        volume.setValue(mediaPlayer.getVolume()*100);
        volume.valueProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volume.getValue()/100);
            }
        });
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                if(!seekSlider.isValueChanging()){
                    seekSlider.setValue(t1.toSeconds());
                }
            }
        });
        seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
            }
        });
        mediaPlayer.stop();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        media = new Media(new File("C:\\Users\\Mothepane\\IdeaProjects\\Task\\src\\main\\resources\\ocean.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }
}