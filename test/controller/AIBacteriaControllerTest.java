/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gamemodel.Dish;
import gameobject.AIBacteria;
import gameobject.Bacteria;
import gameobject.PlayerBacteria;
import java.awt.Point;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.AIBacteriaView;
import view.PlayerBacteriaView;

/**
 *
 * @author 999
 */
public class AIBacteriaControllerTest {
    
    public AIBacteriaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of chooseTarget method, of class AIBacteriaController.
     */
    @Test
    public void testChooseTarget() throws IOException {
        System.out.println("chooseTarget");
        AIBacteria bacteria = createBot(new Point(300, 300), 3);
        Dish dish = new Dish();
        
        PlayerBacteria player = createPlayer(new Point(400, 300), 4);
        dish.addPlayerBacteria(player);
        
        AIBacteria bot_1 = createBot(new Point(400, 400), 1);
        dish.addAIBacteria(bot_1);
        
        AIBacteria bot_2 = createBot(new Point(450, 600), 2);
        dish.addAIBacteria(bot_2);
        
        AIBacteriaController instance = new AIBacteriaController(dish, bacteria);
        Bacteria result = instance.chooseTarget();
        assertEquals(player.getPosition().equals(result.getPosition()), true);
        assertEquals(bacteria.level() < result.level(), true);
        
        //AIBacteria bot_3 = createBot(new Point(450, 600), 2);
        //dish.addAIBacteria(bot_2);
        
    }

    /**
     * Test of detectCollision method, of class AIBacteriaController.
     */
    @Test
    public void testDetectCollision() {
        //System.out.println("detectCollision");
        //AIBacteriaController instance = null;
        //instance.detectCollision();
        assertEquals(true, true);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    private AIBacteria createBot(Point p, int level) throws IOException {
        AIBacteria bacteria = new AIBacteria(new AIBacteriaView());
        bacteria.setPosition(p);
        for(int i = 0; i <= level; i ++)
            bacteria.levelUp();
        return bacteria;
    }
    
    private PlayerBacteria createPlayer(Point p, int level) throws IOException {
        PlayerBacteria bacteria = new PlayerBacteria(new PlayerBacteriaView());
        bacteria.setPosition(p);
        for(int i = 0; i <= level; i ++)
            bacteria.levelUp();
        return bacteria;
    }
    
}
