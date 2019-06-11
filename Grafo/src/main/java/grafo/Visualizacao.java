package grafo;

import org.jgrapht.ext.*;
import org.jgrapht.graph.DefaultEdge;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jgrapht.Graph;

public class Visualizacao{

	public void visualizar(Graph<String, DefaultEdge> grafo) 
	{
		File file = new File("dataset.png");
		BufferedImage grafoImagem;
		
		JGraphXAdapter<String, DefaultEdge> visualizacaoGrafo = new JGraphXAdapter<String, DefaultEdge>(grafo);
		mxFastOrganicLayout design = new mxFastOrganicLayout(visualizacaoGrafo);
		
		visualizacaoGrafo.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
		design.setForceConstant(100);
		design.setDisableEdgeStyle(false);
		design.execute(visualizacaoGrafo.getDefaultParent());
		
				
        if(!file.exists()) {
        	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
        }
        
		grafoImagem = mxCellRenderer.createBufferedImage(visualizacaoGrafo, null, 5, Color.WHITE, true, null);
		try {
			ImageIO.write(grafoImagem, "PNG", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
}
