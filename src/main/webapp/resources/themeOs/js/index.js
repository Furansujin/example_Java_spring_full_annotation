
/* Jquery UI */



newWindows('note', './bureau/chat', 'fa fa-pencil');
newWindows('document', '', 'fa fa-folder');


 $(function() {
  
  //index ref. for all windows
   var zIndex = 1;
  
  // full size window screen
   var fullHeight = $(window).height() - 48,
      fullWidth  = $(window).width(),
      miniHeight =fullHeight/2 ,
      miniWidth  = fullWidth/2 ;

 
  // update full size
  $(window).resize(function() {
    fullHeight = $(window).height() - 48;
    fullWidth = $(window).width();
    miniHeight =fullHeight/2 ;
      miniWidth  = fullWidth/2 ;
  });

 $('.window').resize(function(e) {

    var content =$(this).children("#content");
    content.css("height", $(this).height() - 41);

    content.css("width", $(this).width());

 });


  // autorize resize windows and move (draggable)
  $('.window').resizable({
      maxHeight: fullHeight,
      maxWidth: fullWidth,
      minHeight: 200,
      minWidth: 300 }).draggable({ containment: $("#contentDesktop") ,start: function(e) {
          if ( !$(this).is('.window--active')) {
            $('.window').removeClass('window--active');
          }
          
          $(this).addClass('window--active');
          $(this).css({'z-index' : zIndex++});
          
         // var appName = $(this).data('window');
         // var targetTaskbar = $('.taskbar__item[data-window="' + appName + '"]');
         }
      }).toggle();


$('.menu-start').toggle();
  
 

$(document).mouseup(function(e) {
    var start = $('.menu-start');
    var startToggle = $('.taskbar__item--start');


    if (start.is(':visible') && !startToggle.is(e.target) && startToggle.has(e.target).length === 0 && !start.is(e.target) && start.has(e.target).length === 0 ) {
      $('.menu-start-left').removeClass('transforme_menu-start');    
      $('.menu-start').removeClass('transforme_menu-start');
      setTimeout(function() { $('.menu-start').toggle(); }, 100);
      $('.start-menu-fade').toggle();

    }
 

});

 


  //gere l index des window
    $('.window').mouseup(function(e) {
        if ( !$(this).is('.window--active')) {
          $('.window').removeClass('window--active');
        }
        $('.taskbar__item').removeClass('taskbar__item--open'); 
        $(this).addClass('window--active');
        $(this).css({'z-index' : zIndex++});
        
        var appName = $(this).data('window');
        var targetTaskbar = $('.taskbar__item[data-window="' + appName + '"]');

        targetTaskbar.addClass('taskbar__item--open'); 


  });
    $('iframe').mouseup(function(e) {

     var parentWindow = $(this).closest('.window');
        if ( !parentWindow.is('.window--active')) {
          $('.window').removeClass('window--active');
        }
        $('.taskbar__item').removeClass('taskbar__item--open'); 
        parentWindow.addClass('window--active');
        parentWindow.css({'z-index' : zIndex++});
        
        var appName = parentWindow.data('window');
        var targetTaskbar = $('.taskbar__item[data-window="' + appName + '"]');

        targetTaskbar.addClass('taskbar__item--open'); 


  });
    




  $('.taskbar').each(function() {
   
     $(this).find('.taskbar__item').mousedown(function(e) {
          var nameWindows= $(this).data( "window" );
          var targetWindow = $('.window[data-window="' + nameWindows + '"]');
          $('.taskbar__item').removeClass('taskbar__item--open');
          $(this).addClass('taskbar__item--active taskbar__item--open');
           
          $(targetWindow).css({ 'z-index' : zIndex++ });
          $(targetWindow).animate({
                    top: '48',
                    left: '30%',
                    opacity: '1',
                    height: miniHeight,
                    width: miniWidth
            }).toggle();
          wHeight = miniHeight - 48;
          wWidth = miniWidth -5;
                  var content =$(targetWindow).children("#content");
          content.css("height", wHeight);
          content.css("width", wWidth);


     }); 

     $(this).find('.taskbar__menu_start').mousedown(function(e) {
        var start = $('.start-menu');
    var startToggle = $(this);


    if (!start.is(':visible') &&   !start.is(e.target) ) {
      
         $('.menu-start').toggle();
        $('.menu-start').addClass('transforme_menu-start');
        setTimeout(function() { $('.menu-start-left').addClass('transforme_menu-start'); }, 100);    
          
    }
  
     
       
     });


   }); 




  $('.start-menu__recent').each(function() {
   
     $(this).find('.start-menu__item').mousedown(function(e) {
          var nameWindows= $(this).data( "window" );
          var targetWindow = $('.window[data-window="' + nameWindows + '"]');
          var targetItemTaskbar = $('.taskbar__item[data-window="' + nameWindows + '"]');
          
          $(targetWindow).css({ 'z-index' : zIndex++ });
          $(targetWindow).animate({
                    top: '0%',
                    left: '30%',
                    opacity: '1',
                    height: miniHeight,
                    width: miniWidth
            }).toggle();
          wHeight = miniHeight - 48;
          wWidth = miniWidth -5;
          var content =$(targetWindow).children("#content");
          content.css("height", wHeight);
          content.css("width", wWidth);
          toggleStart();
          $('.menu-start').toggle();
           //  targetWindow.removeClass('taskbar__item--active');
           $('.taskbar__item').removeClass('taskbar__item--open');
           targetItemTaskbar.addClass('taskbar__item--active taskbar__item--open');

     }); 

   


   }); 








$('.menu-toggle').each(function() {
  var menuName = $(this).data('menu');
  var menu = $('.menu[data-menu="' + menuName + '"]');
  var pos = $(this).position();
  var height = $(this).outerHeight();
  
  if ( !$(menu).hasClass('menu--bottom') ) {
    $(menu).position({
      my: 'left top',
      at: 'left bottom',
      of: this,
      collision: 'none'
    });
  } else {
   
  }
  
  $(menu).hide();
  
  $(this).click(function(e) {
    e.preventDefault();
    $('.menu').not(menu).hide();
    $(menu).toggle();
  });
});

$(document).mouseup(function(e) {
  if ( $('.menu').has(e.target).length === 0 && !$('.menu-toggle').is(e.target) && $('.menu-toggle').has(e.target).length === 0 ) {
    $('.menu').hide();
  }
});












$('.window__titlebar').each(function() {
    var parentWindow = $(this).closest('.window');

   $(this).find('.agrandir').mousedown(function(e) {
          $(parentWindow).animate({
                    bot: '0',
                    left: '0',
                    opacity: '1',
                    top: '48',
                    width: fullWidth
            });
          //  var defaultStyle= "{&w_top&:&0%&,&w_right&: &0%&,&w_left&:&0%&,&w_height&:&100%&,&w_width&:&100%&}";
         //   $(parentWindow).data( "style", defaultStyle );
           var content =$(parentWindow).children("#content");
           content.css("height", fullHeight - 48);
            content.css("width", fullWidth - 5);
            $(parentWindow).css("height", fullHeight );
            $(parentWindow).css("width", fullWidth);

       }); 


      $(this).find('.minus').mousedown(function(e) {

          $(parentWindow).animate({
                              top: '48',
                              left: '30%',
                              opacity: '1',
                              height: miniHeight,
                              width: miniWidth
                      });
                            var content =$(parentWindow).children("#content");
              content.css("height", miniHeight-48);
           
              content.css("width", miniWidth-5);


       }); 




    $(this).find('.close').mousedown(function(e) {
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
                  width: miniWidth,
                  height: miniHeight
              });
            var nameWindows= $(parentWindow).data( "window" );
           var targetWindow = $('.taskbar__item[data-window="' + nameWindows + '"]');
             targetWindow.removeClass('taskbar__item--active'); 
             $('.taskbar__item').removeClass('taskbar__item--open');
            
     }); 



     $(this).find('.reduire').mousedown(function(e) {

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
            var nameWindows= $(parentWindow).data( "window" );
            var targetWindow = $('.taskbar__item[data-window="' + nameWindows + '"]');

            $('.taskbar__item').removeClass('taskbar__item--open');
         
     }); 




  }); 


/*
  
   $('#windowToggle').click( // Boutton du menu 
    function(){
        var w_window = $(".window").data( "window" );
        var myRegEx=new RegExp("&","gm");
        var w_style = eval("[" + $(".window").data('style').replace(myRegEx,"\"") + "]");
        var w_top = w_style[0].w_top;
        var w_left= w_style[0].w_left;
        var w_width= w_style[0].w_width;
        var w_height= w_style[0].w_height;
       
       
            if(w_window !="open"){
               
              $('.window').toggle();
              $(".window").data( "window", "open" );
        }else{

             $('.window').toggle();
             $(".window").animate({
                    
            top: w_top,
            left: w_left,
            width: w_width,
            height: w_height,
             opacity: '1'
                });
              console.log("test3");
              $(".window").data( "windowm", "open" );
        

        }
     }
   );
  
  $('.close').click( // Bouton de Fermeture 
    function(){
      console.log($(".window").data('window'));

      //var tab = $(".window").data('style').w_right;
      
       $(".window").animate({
            top: '100%',
            left: '30%',
            opacity: '0',
            height: '0px',
            width: '0px'
        });
     
      $('.window').hide();
      $(".window").animate({
              top: '30%',
              opacity: '1',
            right: '30%',
            width: '30%',
            height: '30%'
        });
      $(".window").data( "window", "close" );
    }
   );
*/
  /*
    $('.reduire').click( // Bouton de Fermeture 
        function(){
            $(".window").animate({
                    top: '100%',
                    left: '30%',
                    opacity: '0',
                    height: '0px',
                    width: '0px'
            }).queue(function() {;
                  $('.window').hide();
                  $( this ).dequeue();
                });
    });
   
  
  $('#declencheur').click(
    function(){
      var textDeclencheur =$(this).text();
      if( textDeclencheur == "Modifier la taille de la fenêtre"){
        $(this).text("Bloquer taille de la fenêtre");
        $('#content').toggle();
        $('.window').resizable();
      }else
      {
        $(this).text("Modifier la taille de la fenêtre");
        $('#content').toggle();
        $('.window').resizable('destroy'); 
      }
      
    }
  );

  */
    
});




//time FR
function date_heure(id)
{
        date = new Date;
        annee = date.getFullYear();
        moi = date.getMonth();
        mois = new Array('01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12');

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
        resultat ='<div id="taskbar__tray_heure">'+h+':'+m+':'+s+'</div><div id="taskbar__tray_date">'+j+'/'+moi+'/'+annee+'</div>';
        document.getElementById(id).innerHTML = resultat;
        setTimeout('date_heure("'+id+'");','1000');
        return true;
}

 
// time UK
function currentTime(id)
{
  date = new Date;
        annee = date.getFullYear();
        moi = date.getMonth();
        mois = new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
        j = date.getDate();
        jour = date.getDay();
        jours = new Array('Sunday', 'Monday', 'Tuesday', 'Tuesday', 'Jeudi', 'Friday', 'Saturday');
        

  var a_p = "";
  var d = new Date();

  var curr_hour = d.getHours();

  if (curr_hour < 12)
     {
     a_p = "AM";
     }
  else
     {
     a_p = "PM";
     }
  if (curr_hour == 0)
     {
     curr_hour = 12;
     }
  if (curr_hour > 12)
     {
     curr_hour = curr_hour - 12;
     }

  var curr_min = d.getMinutes();
  
  if ( curr_min < 10 ) {
    curr_min = '0' + curr_min;
  }

  resultat ='<div id="taskbar__tray_heure">'+curr_hour + ':' + curr_min + ' ' + a_p+'</div><div id="taskbar__tray_date">'+jours[jour]+' '+j+' '+mois[moi]+' '+annee+'</div>';
  document.getElementById(id).innerHTML = resultat;
  setTimeout('currentTime("'+id+'");','1000');
  return true;
}

window.onload = date_heure('taskbar__tray');




function toggleStart(e) {
  $('.start-menu-fade').fadeToggle(500);
  $('.start-menu').fadeToggle(250).toggleClass('start-menu--open');
  
}


$('.taskbar__item--start').click(toggleStart);


$(function() {
  $('.taskbar__item--start').click(function() {
    $(this).removeClass('taskbar__item--open taskbar__item--active');
  });
});

/*
$(document).mouseup(function(e) {
    var start = $('.start-menu');
    var startToggle = $('.taskbar__item--start');


    if (start.is(':visible') && !startToggle.is(e.target) && startToggle.has(e.target).length === 0 && !start.is(e.target) && start.has(e.target).length === 0 ) {
      toggleStart();
      console.log('aaaaaa');
      //alert('clicked outside start');
    }
  
  

});*/