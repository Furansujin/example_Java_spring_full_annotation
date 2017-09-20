








function newWindows(nameWindows, contentWindows, classItem)
{

$( ".desktop" ).append( '<div class="window" data-window="'+nameWindows+'" data-etat="close" ><div class="window__titlebar"> <div class="window__controls window__controls--right"><a class="window__minimize reduire" href="#"><i class="fa fa-minus"></i></a><a class="window__minus minus" href="#"><i class="fa fa-minus-square-o"></i></a> <a class="window__maximize agrandir" href="#"><i class="fa fa-plus-square-o"></i></a> <a class="window__close close" href="#"><i class="fa fa-close"></i></a> </div>  <span class="window__title">  '+nameWindows+'   </span>  </div>  <div id="content">   <iframe style="height : 100%; width: 100%;" src="'+contentWindows+'"></iframe></div></div>' );

$( ".taskbar" ).append('<a class="taskbar__item" href="#" data-window="'+nameWindows+'"><i class="'+classItem+'"></i></a>');

$( ".start-menu__recent" ).append('<li class="start-menu__item" data-window="'+nameWindows+'"> <a href="#">  <i class="'+classItem+'"></i>  '+nameWindows+' </a>  </li>');


}
















/*        var windowId = $("#"+idWindow);
        var w_window = windowId.data( "window" );
        var myRegEx=new RegExp("&","gm");
        var w_style = eval("[" + windowId.data('style').replace(myRegEx,"\"") + "]");
        var w_top = w_style[0].w_top;
        var w_left= w_style[0].w_left;
        var w_width= w_style[0].w_width;
        var w_height= w_style[0].w_height;
       
       
        if(w_window !="open"){
               
              windowId.toggle();
              windowId.data( "window", "open" );
          }
          else{

              windowId.toggle();
              windowId.animate({
                    
              top: w_top,
              left: w_left,
              width: w_width,
              height: w_height,
              opacity: '1'
          });
          console.log("test3");
          windowId.data( "window", "open" );
        
        }
     }
};


function reduireWindows(idWindow){
        var windowId = $("#"+idWindow);
        windowId.animate({
                    top: '100%',
                    left: '30%',
                    opacity: '0',
                    height: '0px',
                    width: '0px'
            }).queue(function() {;
                  windowId.hide();
                  $( this ).dequeue();
                });
    };

    function reduireWindows(idWindow){
        var windowId = $("#"+idWindow);
           windowId.animate({
                    top: '0%',
                    left: '0%',
                    opacity: '1',
                    height: '100%',
                    width: '100%'
            });
            var defaultStyle= "{&w_top&:&0%&,&w_right&: &0%&,&w_left&:&0%&,&w_height&:&100%&,&w_width&:&100%&}";
            windowId.data( "style", defaultStyle );

    };

   function closeWindows(idWindow){
      var windowId = $("#"+idWindow);

      windowId.animate({
            top: '100%',
            left: '30%',
            opacity: '0',
            height: '0px',
            width: '0px'
        });
     
      windowId.hide();
      windowId.animate({
              top: '30%',
              opacity: '1',
            right: '30%',
            width: '30%',
            height: '30%'
        });
      windowId.data( "window", "close" );
    }

*/






    /**
 $(function() {
    $( "#resizable" ).resizable({
      maxHeight: 200,
      maxWidth: 300,
      minHeight: 150,
      minWidth: 200
    }).draggable({
                handle: "#handle"
        });
  });







var appName = $("#"+id).data('window');
    var targetTaskbar = $('.window[data-window="' + appName + '"]');
      var windowId = $("#"+idWindow);
























/* Jquery UI 

$(function() {
  
  //index ref. for all windows
   var zIndex = 1;
  
  // full size window screen
   var fullHeight = $(window).height() - 48,
      fullWidth  = $(window).width();
 
  // update full size
  $(window).resize(function() {
    fullHeight = $(window).height() - 48;
    fullWidth = $(window).width();
  });

  // autorize resize windows and move (draggable)
  $('.window').resizable({
      maxHeight: fullHeight,
      maxWidth: fullWidth,
      minHeight: 200,
      minWidth: 300
    }).draggable({ containment: $("#desktop") ,start: function(e) {
          if ( !$(this).is('.window--active')) {
            $('.window').removeClass('window--active');
          }
          
          $(this).addClass('window--active');
          $(this).css({'z-index' : zIndex++});
          
          var appName = $(this).data('window');
          var targetTaskbar = $('.taskbar__item[data-window="' + appName + '"]');
         }
      }); 
  





  //$('#content').hide();
  
  

  //gere l index des window
    $('.window').mousedown(function(e) {
        if ( !$(this).is('.window--active')) {
          $('.window').removeClass('window--active');
        }
        
        $(this).addClass('window--active');
        $(this).css({'z-index' : zIndex++});
        
        var appName = $(this).data('window');
        var targetTaskbar = $('.taskbar__item[data-window="' + appName + '"]');
  });

  $('.taskbar').each(function() {
   
   $(this).find('.taskbar__item').click(function(e) {
        var nameWindows= $(this).data( "window" );
        var targetWindow = $('.window[data-window="' + nameWindows + '"]');

        var w_window = $(targetWindow).data( "etat" );
        
       
       
            if(w_window !="open"){
               
              $(targetWindow).toggle();
              $(targetWindow).data( "etat", "open" );
            }else{

                 $(targetWindow).toggle();
                 $(targetWindow).animate({
                        
                     top: '0%',
                    left: '0%',
                    opacity: '1',
                    height: fullHeight,
                    width: fullWidth
                    });
                  
                  $(targetWindow).data( "etat", "open" );
            }

    }); 


 }); 



// window__titlebar action
   $('.window__titlebar').each(function() {
    var parentWindow = $(this).closest('.window');

   $(this).find('.agrandir').click(function(e) {
          $(parentWindow).animate({
                    top: '0%',
                    left: '0%',
                    opacity: '1',
                    height: fullHeight,
                    width: fullWidth
            });
            var defaultStyle= "{&w_top&:&0%&,&w_right&: &0%&,&w_left&:&0%&,&w_height&:&100%&,&w_width&:&100%&}";
            $(parentWindow).data( "style", defaultStyle );
    }); 



    $(this).find('.close').click(function(e) {

          $(parentWindow).animate({
            top: '100%',
            left: '30%',
            opacity: '0',
            height: '0px',
            width: '0px'
        });
     
      $(parentWindow).hide();
      $(parentWindow).animate({
              top: '30%',
              opacity: '1',
            right: '30%',
            width: '30%',
            height: '30%'
        });
      $(parentWindow).data( "window", "close" );
         
     }); 



     $(this).find('.reduire').click(function(e) {

            $(parentWindow).animate({
                    top: '100%',
                    left: '30%',
                    opacity: '0',
                    height: '0px',
                    width: '0px'
            }).queue(function() {;
                  $(parentWindow).hide();
                  $( this ).dequeue();
                });
         
     }); 




    }); 






function date_heure(id)
{
        date = new Date;
        annee = date.getFullYear();
        moi = date.getMonth();
        mois = new Array('Janvier', 'F&eacute;vrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Ao&ucirc;t', 'Septembre', 'Octobre', 'Novembre', 'D&eacute;cembre');
        j = date.getDate();
        jour = date.getDay();
        jours = new Array('Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi');
        h = date.getHours();
        if(h<10)
        {
                h = "0"+h;
        }
        m = date.getMinutes();
        if(m<10)
        {
                m = "0"+m;
        }
        s = date.getSeconds();
        if(s<10)
        {
                s = "0"+s;
        }
        resultat ='<div id="taskbar__tray_heure">'+h+':'+m+':'+s+'</div><div id="taskbar__tray_date">'+jours[jour]+' '+j+' '+mois[moi]+' '+annee+'</div>';
        document.getElementById(id).innerHTML = resultat;
        setTimeout('date_heure("'+id+'");','1000');
        return true;
}


window.onload = date_heure('taskbar__tray');









  */
 
