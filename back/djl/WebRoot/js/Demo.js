/**
 * 表格插件面板
 * @param $
 */
(function($) {
	
	var defaults = {
		method : 'POST',
		dataType : 'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		//url:sysUrl+url,
		paginationLoop : true,
		pageList : [ 20, 40, 60, 80, 100 ],
		pageNumber : 1,
		showHeader : true,
		showFooter : false,
		showPaginationSwitch : false,
		showColumns:true,
		showRefresh:true,
		toolbar:'#toolbar',
		pageSize : 20,
		sidePagination : "server",
		pagination : true,
		//queryParams : queryParams,
		//columns : columns,
		responseHandler : function(res) {
			var result = res.data;
			return {
				rows : result.list,
				total : result.totalRow
			};
		}
	};
	
	/**
	 * 表哥插件
	 */
	$.fn.TablePanel = function (options){
		var $tablePanel = $(this),
			$table = $tablePanel.find('table'), //表格界面
			modelName = $table.attr('model-name'), // modelName
			$searchForm = $tablePanel.find('form'); // 搜索表单
		
		if(options.url){
			options.url = "viewList/"+modelName;
		}
		var options = $.extend({}, defaults, options); 
		if(options.queryParams){
			options.queryParams = function(params) {
				if($searchForm){
					params.where = JSON.stringify($searchForm.serializeObject());
				}
				return params;
			}
		}
		
		if($searchForm){
			var $searchButton = $searchForm.find('.table-search-button');//搜索按钮
			$searchForm.keyup(function(event){
				if(event.keyCode==13){
					$table.bootstrapTable('refresh');
				}
			});
			$searchButton.on('click',function(){
				$table.bootstrapTable('refresh');
			});
		}
		$table.bootstrapTable(options);
		
		return {
			
		}
	};
	
})(JQuery);