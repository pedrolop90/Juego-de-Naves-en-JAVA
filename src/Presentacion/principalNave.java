

package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class principalNave extends JFrame implements KeyListener{
    
    private nave nave;
    
    public principalNave(){
        setSize(800,600);
        setResizable(false);
        addKeyListener(this);
        nave=new nave(this.getWidth(),this.getHeight());
        nave.star();
        add(nave);
    }
    
    public static void main(String[] args) {
        principalNave p=new principalNave();
        p.setVisible(true);
        p.setDefaultCloseOperation(3);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_A&&nave.naveX>=10){
            nave.naveX-=13;
        } 
        if(e.getKeyCode()==KeyEvent.VK_D&&nave.naveX<=(nave.totalX-127)){
            nave.naveX+=13;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE&&nave.detenerDisparo){
            nave.disparo=true;
            nave.detenerDisparo=false;
        }
        if((e.getKeyChar()=='r'||e.getKeyChar()=='R')&&(nave.matarAliado||nave.matarEnemigo)){
            nave.reiniciar();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    private class nave extends JPanel{
        
        private int totalX;
        private int totalY;
        private int naveY;
        private int naveX;
        private ImageIcon imagenNave;
        private javax.swing.Timer t;
        private int disparoX;
        private int disparoY;
        private ImageIcon imagenDisparo;
        private boolean disparo=false;
        private boolean repetirDisparo=false;
        private boolean detenerDisparo=true;
        private ImageIcon imagenEnemigo;
        private int enemigoX;
        private int enemigoY;
        private boolean enemigoVivo=true;
        private boolean aliadoVivo=true;
        private boolean caminar=false;
        private boolean matarEnemigo=false;
        private boolean matarAliado=false;
        private ImageIcon imagenBalaEnemigo;
        private int velocidadCaminarEnemigo=60;
        private int balaEnemigaX;
        private int balaEnemigaY;
        private boolean disparoEnemigo=false;
        private boolean repetirDisparoEnemigo=false;
        private boolean detenerDisparoEnemigo=true;
        private int tiempo=0;
        
        public nave(int tX,int tY){
            setBackground(Color.BLACK);
            totalX=tX;
            totalY=tY;
            imagenNave=new ImageIcon("src/imagenes/nave.png");
            imagenDisparo=new ImageIcon("src/imagenes/bala.png");
            imagenEnemigo=new ImageIcon("src/imagenes/naveEnemiga.png");
            imagenBalaEnemigo=new ImageIcon("src/imagenes/balaEnemigo.png");
            naveY=totalY-imagenNave.getIconHeight()-60;
            enemigoX=totalX-imagenEnemigo.getIconWidth()-127;
            enemigoY=23;
            t=new javax.swing.Timer(100,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tiempo+=100;
                    if (!matarEnemigo&&!matarAliado) {
                        if (disparo && !repetirDisparo) {
                            disparoX = naveX + (imagenNave.getIconWidth() / 2) - 10;
                            disparoY = naveY;
                            repetirDisparo = true;
                        } else if (disparo && repetirDisparo && disparoY >= 0) {
                            disparoY -= 40;
                            if((disparoY<=(enemigoY+imagenEnemigo.getIconHeight()))&&(disparoX>=enemigoX&&disparoX<=(enemigoX+imagenEnemigo.getIconWidth()))){
                                matarEnemigo=true;
                            }
                        } else if (disparo && repetirDisparo && disparoY < 0 && !detenerDisparo) {
                            disparo = false;
                            repetirDisparo = false;
                            detenerDisparo = true;
                        }
                    }else{
                         enemigoVivo=false;
                         disparo = false;
                         repetirDisparo = false;
                         detenerDisparo = true;
                         stop();
                    }
                    
                    if(tiempo==1000){
                        enemigoY+=10;
                        tiempo=0;
                    }
                    
                    if(!caminar){
                        enemigoX+=velocidadCaminarEnemigo;
                        if(enemigoX>=totalX-190){
                            enemigoX-=velocidadCaminarEnemigo;
                            caminar=true;
                        }
                    }else{
                        enemigoX-=velocidadCaminarEnemigo;
                        if(enemigoX<=0){
                             enemigoX+=velocidadCaminarEnemigo;
                            caminar=false;
                        }
                    }
                    
                    if(naveX>=enemigoX-50&&(naveX + imagenNave.getIconWidth())<=(enemigoX+imagenEnemigo.getIconWidth()+50)){
                        disparoEnemigo=true;
                        detenerDisparoEnemigo = true;
                        repetirDisparoEnemigo = false;
                    }
                    if (!matarAliado) {
                        if (disparoEnemigo && !repetirDisparoEnemigo) {
                            balaEnemigaX = enemigoX + (imagenEnemigo.getIconWidth()/2);
                            balaEnemigaY=enemigoY+imagenEnemigo.getIconHeight();
                            repetirDisparoEnemigo = true;
                        } else if (disparoEnemigo && repetirDisparoEnemigo && balaEnemigaY <= totalY) {
                            balaEnemigaY += 60; 
                             if(balaEnemigaY>=naveY&&(((balaEnemigaX>=naveX)&&(balaEnemigaX<=naveX+imagenNave.getIconWidth()))||
                                ((((balaEnemigaX-50)>=naveX)&&((balaEnemigaX-50)<=naveX+imagenNave.getIconWidth())))||
                                (((balaEnemigaX+50)>=naveX)&&((balaEnemigaX+50)<=naveX+imagenNave.getIconWidth())))){
                                matarAliado=true;
                            }
                        } else if (disparoEnemigo && repetirDisparoEnemigo && balaEnemigaY < (totalY - imagenNave.getIconHeight() - 60) && !detenerDisparoEnemigo) {
                            disparoEnemigo = true;
                            repetirDisparoEnemigo = false;
                            detenerDisparoEnemigo = true;
                        }
                    }else{
                        aliadoVivo=false;
                        enemigoVivo=true;
                        repetirDisparoEnemigo=false;
                        detenerDisparoEnemigo=false;
                        disparoEnemigo=false;
                        stop();
                    }
                    
                   update();
                }
            });
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            fondo(g);
         if(aliadoVivo){
              crearNave(g);
          }else{
             ImageIcon img=new ImageIcon("src/imagenes/perdiste.png");
             g.drawImage(img.getImage(), (totalX/2)-img.getIconWidth(), (totalY/2)-img.getIconHeight(), img.getIconWidth(), img.getIconHeight(), this);
         }
         if(disparo&&aliadoVivo){
             disparoNave(g);
         }
         if(enemigoVivo){
            naveEnemiga(g); 
         }else{
             ImageIcon img=new ImageIcon("src/imagenes/ganaste.png");
             g.drawImage(img.getImage(), (totalX/2)-img.getIconWidth(), (totalY/2)-img.getIconHeight(), img.getIconWidth(), img.getIconHeight(), this);
         }
         if(disparoEnemigo&&enemigoVivo){
             disparoEnemigo(g);
         }
        }
        public void fondo(Graphics g){
            Graphics2D g2=(Graphics2D) g;
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(2));
            g.drawRect(20, 20, totalX-60, totalY-80);
        }
        public void crearNave(Graphics g){
             g.drawImage(imagenNave.getImage(), naveX,naveY, imagenNave.getIconWidth(), imagenNave.getIconHeight(), this);
        }
        public void disparoNave(Graphics g){
             g.drawImage(imagenDisparo.getImage(), disparoX,disparoY, imagenDisparo.getIconWidth(), imagenDisparo.getIconHeight(), this);
        }
        
        public void naveEnemiga(Graphics g){
            g.drawImage(imagenEnemigo.getImage(), enemigoX, enemigoY,imagenEnemigo.getIconWidth(),imagenEnemigo.getIconHeight(), this);
        }
        
        public void disparoEnemigo(Graphics g){
            g.drawImage(imagenBalaEnemigo.getImage(), balaEnemigaX, balaEnemigaY,imagenBalaEnemigo.getIconWidth(),imagenBalaEnemigo.getIconHeight(), this);
            g.drawImage(imagenBalaEnemigo.getImage(), balaEnemigaX-50, balaEnemigaY,imagenBalaEnemigo.getIconWidth(),imagenBalaEnemigo.getIconHeight(), this);
            g.drawImage(imagenBalaEnemigo.getImage(), balaEnemigaX+50, balaEnemigaY,imagenBalaEnemigo.getIconWidth(),imagenBalaEnemigo.getIconHeight(), this);
        }
        
        public void reiniciar(){
            disparo = false;
            repetirDisparo = false;
            detenerDisparo = true;
            enemigoVivo = true;
            aliadoVivo = true;
            caminar = false;
            matarEnemigo = false;
            matarAliado = false;
            velocidadCaminarEnemigo = 60;
            repetirDisparoEnemigo = false;
            detenerDisparoEnemigo = true;
            naveY=totalY-imagenNave.getIconHeight()-60;
            enemigoX=totalX-imagenEnemigo.getIconWidth()-127;
            enemigoY=23;
            naveX=10;
            star();
        }
        
        public void update(){
            this.repaint();
        }
        public void star(){
            t.start();
        }
        public void stop(){
            t.stop();
        }

        public int getNaveX() {
            return naveX;
        }

        public void setNaveX(int naveX) {
            this.naveX = naveX;
        }
        
        
    }
}
