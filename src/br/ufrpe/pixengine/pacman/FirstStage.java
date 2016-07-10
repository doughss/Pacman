package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.components.State;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import br.ufrpe.pixengine.pacman.PacMan;


public class FirstStage extends State {
	public FirstStage() {
		manager.addObject(new GameImage("pacman/floor.png"));
		
		// mapa do labirinto
		int maze_matrice[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						        {0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0},
						        {0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0},
						        {0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0},
						        {0,0,0,1,0,0,1,0,0,2,2,0,0,1,0,0,1,0,0,0},
						        {1,1,1,1,1,1,1,0,2,2,2,2,0,1,1,1,1,1,1,1},
						        {0,0,0,1,0,0,1,0,2,2,2,2,0,1,0,0,1,0,0,0},
						        {0,1,1,1,1,1,1,0,2,2,2,2,0,1,1,1,1,1,1,0},
						        {0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0},
						        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
						        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
		this.build_maze(manager, maze_matrice);
		
		manager.addObject(new PacMan(420, 414, "pacman", manager));
	}
	
	/**
	 * Função para montar o labirinto
	 * 
	 * @param game_input: Controlador de input do jogo
	 */
	public static void build_maze(ObjectManager manager, int maze_matrice[][]){
		int point_count = 0;

		for (int i = 0; i < maze_matrice.length; i++) {
			int[] row =  maze_matrice[i];
			for (int j = 0; j < row.length; j++) {
				if(row[j] == 0){
					manager.addObject(new Wall(j, i));
				} else if (row[j] == 1) {
					String tag = "point_" + point_count;
					if((i==1 & j==1)||(i==1 & j==18)||(i==9 & j==1)||(i==9 & j==18)){
						manager.addObject(new GreenPoint(j, i, tag, manager));
					}else{
						manager.addObject(new GrayPoint(j, i, tag, manager));
					}
					point_count++;
				}			
			}
		}	
	}

	@Override
	public void update(GameContainer gc, float dt) {
		manager.updateObjects(gc, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		manager.renderObjects(gc, r);
	}

	@Override
	public void dipose() {
		manager.diposeObjects();
	}

	public ObjectManager getManager() {
		return manager;
	}

	public void setManager(ObjectManager manager) {
		this.manager = manager;
	}

}
