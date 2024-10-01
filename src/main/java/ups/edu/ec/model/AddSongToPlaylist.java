package ups.edu.ec.model;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * 
 * @author grupo_3
 */
public class AddSongToPlaylist implements ICommand {
    private String songTitle;
    private String playlistName;
    private DefaultMutableTreeNode playlistNode;
    private DefaultTreeModel treeModel;
    private JLabel feedbackLabel;

    public AddSongToPlaylist(String songTitle, String playlistName,
                             DefaultMutableTreeNode playlistNode, DefaultTreeModel treeModel, JLabel feedbackLabel) {
        this.songTitle = songTitle;
        this.playlistName = playlistName;
        this.playlistNode = playlistNode;
        this.treeModel = treeModel;
        this.feedbackLabel = feedbackLabel;
    }

    @Override
    public void execute() {
        // Verificar si la playlist es válida
        if (playlistNode == null) {
            feedbackLabel.setText("La playlist no existe. Crea la playlist primero.");
            return;
        }

        // Agregar la canción al nodo de la playlist
        DefaultMutableTreeNode songNode = new DefaultMutableTreeNode(songTitle);
        playlistNode.add(songNode);
        treeModel.reload();  // Refrescar el árbol

        // Mostrar mensaje de éxito en la etiqueta
        feedbackLabel.setText("Canción " + songTitle + " agregada a la playlist " + playlistName);
    }
}
