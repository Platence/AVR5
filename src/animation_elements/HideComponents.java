package animation_elements;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;

public class HideComponents {

    private ArrayList<Node> listNode = new ArrayList<>();

    public HideComponents(ArrayList<Node> listNode) {
        this.listNode = listNode;
        setToAllNodeVisionProperty();
    }

    private void setToAllNodeVisionProperty(){
        for (Node node : this.getListNode()){
             setSpecificVisionTocurrent(node);
        }
    }

    private void setSpecificVisionTocurrent(Node node){
        node.setOnMouseEntered(e->setOpacityLowFowAll(node));
        node.setOnMouseExited(e->setOpacitiHigh());

    }

    private void setOpacityLowFowAll(Node node){
        for (Node all : this.getListNode()){
            if(all.getId().equals(node.getId())){continue;}
            FadeTransition tt = new FadeTransition(Duration.millis(350),all);
            tt.setToValue(0.61);
            tt.play();
            //all.setOpacity(0.25);
        }
    }

    private void setOpacitiHigh(){
        for (Node all : this.getListNode()){
            FadeTransition tt = new FadeTransition(Duration.millis(350),all);
            tt.setToValue(1.0);
            tt.play();
        }
    }


    public ArrayList<Node> getListNode() {
        return listNode;
    }
}
