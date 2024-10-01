package ups.edu.ec.model;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Map;

/**
 * 
 * @author grupo_3
 */
public class CreatePlaylist implements ICommand {
    private String playlistName;
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;
    private Map<String, DefaultMutableTreeNode> playlistNodes;
    private JLabel feedbackLabel;

    public CreatePlaylist(String playlistName, DefaultMutableTreeNode rootNode,
                          DefaultTreeModel treeModel, Map<String, DefaultMutableTreeNode> playlistNodes, JLabel feedbackLabel) {
        this.playlistName = playlistName;
        this.rootNode = rootNode;
        this.treeModel = treeModel;
        this.playlistNodes = playlistNodes;
        this.feedbackLabel = feedbackLabel;
    }

    @Override
    public void execute() {
        // Verificar si la playlist ya existe
        if (playlistNodes.containsKey(playlistName)) {
            feedbackLabel.setText("La playlist '" + playlistName + "' ya existe.");
            return;
        }

        // Crear un nuevo nodo para la playlist
        DefaultMutableTreeNode playlistNode = new DefaultMutableTreeNode(playlistName);
        rootNode.add(playlistNode);
        playlistNodes.put(playlistName, playlistNode);

        // Refrescar el árbol
        treeModel.reload(rootNode);

        // Mostrar mensaje de éxito en la etiqueta
        feedbackLabel.setText("Playlist '" + playlistName + "' creada con éxito.");
    }
}
