package com.hct.factory;

public interface FactoryFun {
   Hct_Config.ST_FACTORY_CONFIG getFactoryConfig();

   Hct_Config.ST_FACTORY_EXT_CONFIG getFactoryExtConfig();

   int getPanelCtl();

   String getParameters(String var1);

   boolean isMcuG48Ver();

   void setParameters(String var1);
}
