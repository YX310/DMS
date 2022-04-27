/**
 * Simple Load More
 *
 * Version: 1.0.2
 * Author: Zeshan Ahmed
 * Website: https://zeshanahmed.com/
 * Github: https://github.com/zeshanshani/simple-load-more/
 */
(function($) {
  $.fn.simpleLoadMore = function( options ) {
    // Settings.
    const settings = $.extend({
      count: 5,
      btnHTML: '',
      item: ''
    }, options);

    // Variables
    const $loadMore = $(this);

    // Run through all the elements.
    $loadMore.each(function(i, el) {

      // Variables.
      const $thisLoadMore = $(this);
      const $items = $thisLoadMore.find(settings.item);
      const btnHTML = settings.btnHTML ? settings.btnHTML : '<a href="#" class="load-more__btn">查看更多<i class="fas fa-angle-down"></i></a>';
      const $btnHTML = $(btnHTML);

      // Add classes
      $thisLoadMore.addClass('load-more');
      $items.addClass('load-more__item');

      // Add button.
      if ( ! $thisLoadMore.find( '.load-more__btn' ).length && $items.length > settings.count ) {
        $thisLoadMore.append( $btnHTML );
      }

      let $btn = $thisLoadMore.find('.load-more__btn');

      // Check if button is not present. If not, then attach $btnHTML to the $btn variable.
      if ( ! $btn.length ) {
        $btn = $btnHTML;
      }

      if ( $items.length > settings.count ) {
        $items.slice(settings.count).hide();
      }

      // Add click event on button.
      $btn.on('click', function(e) {
        e.preventDefault();

        var $this = $(this),
        $updatedItems = $items.filter(':hidden').slice(0, settings.count);

        // Show the selected elements.
        if ( $updatedItems.length > 0 ) {
          $updatedItems.fadeIn();
        }

        // Hide the 'View More' button
        // if the elements lenght is less than 5.
        if ( $updatedItems.length < settings.count ) {
          $this.remove();
        }
      });
    });
  }
}( jQuery ));