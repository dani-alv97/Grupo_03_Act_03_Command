package ups.edu.ec.view;

import ups.edu.ec.controller.ManagerCommand;
import ups.edu.ec.controller.ReproductorInvoker;
import ups.edu.ec.model.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import ups.edu.ec.controller.MusicController;

/**
 * 
 * @author grupo_3
 */
public class MusicAppGUI extends JFrame {
    private MusicController musicController;  // Controlador central
    private JLabel playingLabel;
    private JTextField songTitleField;
    private JTextField playlistNameField;
    private JTree playlistTree;
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;
    private Map<String, DefaultMutableTreeNode> playlistNodes;
    private DefaultMutableTreeNode currentPlaylistNode;
    private DefaultMutableTreeNode currentSongNode;

    private ManagerCommand commandManager;
    private ReproductorInvoker invoker;

    private JButton addSongToPlaylistButton;
    private JButton createPlaylistButton;
    private JButton playSongButton;
    private JButton stopSongButton;
    private JButton skipSongButton;
    
    public MusicAppGUI() {
        // Instancias del controlador y otros componentes
        musicController = new MusicController();
        playlistNodes = new HashMap<>();
        setupUI();
        
    }

    private void setupUI() {
        // Configuración de la ventana
        setTitle("Reproductor de Musica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Instancias de los gestores de comandos
        commandManager = new ManagerCommand();
        invoker = new ReproductorInvoker();
        playlistNodes = new HashMap<>();

        // Componentes de la interfaz
        JLabel playlistLabel = new JLabel("Playlist:");
        playlistLabel.setBounds(20, 20, 100, 25);
        add(playlistLabel);

        playlistNameField = new JTextField();
        playlistNameField.setBounds(120, 20, 200, 25);
        add(playlistNameField);

        createPlaylistButton = new JButton("Crear Playlist");
        createPlaylistButton.setBounds(120, 60, 150, 25);
        add(createPlaylistButton);
        
        JLabel songLabel = new JLabel("Canción:");
        songLabel.setBounds(20, 100, 100, 25);
        add(songLabel);

        songTitleField = new JTextField();
        songTitleField.setBounds(120, 100, 200, 25);
        add(songTitleField);

        addSongToPlaylistButton = new JButton("Agregar Canción a Playlist");
        addSongToPlaylistButton.setBounds(120, 140, 200, 25);
        add(addSongToPlaylistButton);

        playingLabel = new JLabel("Seleccione una canción para reproducir");
        playingLabel.setBounds(20, 200, 300, 50);
        add(playingLabel);
        
        // Botones de control (Play, Stop, Skip)
        playSongButton = new JButton("Play");
        playSongButton.setBounds(20, 260, 100, 25);
        add(playSongButton);

        stopSongButton = new JButton("Stop");
        stopSongButton.setBounds(130, 260, 100, 25);
        add(stopSongButton);

        skipSongButton = new JButton("Skip");
        skipSongButton.setBounds(240, 260, 100, 25);
        add(skipSongButton);

        // Etiqueta para mostrar el estado de reproducción
        

        // Árbol para mostrar playlists y canciones
        rootNode = new DefaultMutableTreeNode("Playlists");
        treeModel = new DefaultTreeModel(rootNode);
        playlistTree = new JTree(treeModel);
        JScrollPane treeScrollPane = new JScrollPane(playlistTree);
        treeScrollPane.setBounds(350, 20, 220, 300);
        add(treeScrollPane);

        playSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) playlistTree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.isLeaf()) {
                    currentSongNode = selectedNode;
                    currentPlaylistNode = (DefaultMutableTreeNode) selectedNode.getParent();
                    String songTitle = selectedNode.getUserObject().toString();
                    Song song = new Song(songTitle, "", "");

                    // Usar el controlador para reproducir la canción
                    musicController.playSong(song, playingLabel);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor selecciona una canción para reproducir.");
                }
            }
        });

        stopSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Usar el controlador para detener la canción
                musicController.stopSong(playingLabel);
            }
        });

        skipSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentSongNode != null && currentPlaylistNode != null) {
                    // Usar el controlador para saltar a la siguiente canción
                    musicController.skipNextSong(playlistTree, currentPlaylistNode, currentSongNode, playingLabel);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay canción en reproducción para saltar.");
                }
            }
        });

        createPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playlistName = playlistNameField.getText();
                // Usar el controlador para crear una playlist
                musicController.createPlaylist(playlistName, rootNode, treeModel, playlistNodes, playingLabel);
            }
        });

        addSongToPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String songTitle = songTitleField.getText();
                String playlistName = playlistNameField.getText();
                DefaultMutableTreeNode playlistNode = playlistNodes.get(playlistName);
                // Usar el controlador para agregar la canción a la playlist
                musicController.addSongToPlaylist(songTitle, playlistName, playlistNode, treeModel, playingLabel);
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusicAppGUI().setVisible(true);
            }
        });
    }
}
