   M S B A M L   `   `   `  ����G  CHostelWPFApp, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null+clr-namespace:HostelWPFAppHostelWPFApp  5   	       HostelWPFApp.MainWindow   5   	   W SPresentationCore, Version=6.0.2.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=6.0.2.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=6.0.2.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=6.0.2.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=6.0.2.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   ,localclr-namespace:HostelWPFApp     5   	   _ [MaterialDesignThemes.Wpf, Version=4.7.1.0, Culture=neutral, PublicKeyToken=df2a72020bd7962aFmaterialDesign1http://materialdesigninxaml.net/winfx/xaml/themes  5   	   -   5          � Title$  
MainWindow��5	   	   $	��910��6   $
��1399��6)    � WindowStartupLocation$ CenterScreen=�66    � WindowStyle$
 None=�6[    � 
ResizeMode$ NoResize=�6n    � AllowsTransparency$
 True��6�   ��6�   Z� .��5
      � .+�5   
   #  MaterialDesignThemes.Wpf.Card  $��10q�6      UniformCornerRadius$ 15Q�6*   .��5      �  � ColumnDefinitions 5      �� 5      $��90 �6'   64   �� 5      $��* �6'   63    � 	Resources 5      '  !MaterialDesignThemes.Wpf.PackIcon �� 
TargetType	 �� Property
 �� Value��%�   '        �� 5      "  6   .�5      �� 	 � ��$
 30��62   6?   �� 5      	 � ��$
 30��63   6@   5      5      .+�5      � $��0��6   .+�5      �� $
��0 15q�6   .@�5      �� $��Center=�6'   $��	0 40 0 10q�6D   .��5      ��  &  "MaterialDesignFloatingActionButton#��[  6%   $
��Left=�6a   
���   ���5   $   Z� ��6;   Z� .��5   "   �� $��-5q�6-   .+�5   &        Kind$ Resistor=�6>   6P   5   #   5      5       �� 5!      $��Center=�6'   $��0 q�6D   .��5"      �� #��[  6%   $
��Left=�6a   
���   ���5#   $   Z� ��6;   Z� .��5$   "   �� $��-5q�6-   .+�5%   &     $ CircleSlice6=�6>   6T   5&   #   5'      5(      �� 5)      $��Center=�6'   $
��0 10q�6D   .��5*      �� #��[  6%   $
��Left=�6a   
���   ���5+   $   Z� ��6;   Z� .��5,   "   �� $��-5q�6-   .+�5-   &     $ CalendarBlankOutline=�6>   6\   5.   #   5/      50      �� 51      $��Center=�6'   $��0 q�6D   .��52      �� #��[  6%   $
��Left=�6a   
���   ���53   $   Z� ��6;   Z� .��54   "   �� $��-5q�6-   .+�55   &     $ EqualiserVertical=�6>   6Y   56   #   57      58      �� 59      $��Center=�6'   $
��0 10q�6D   .��5:      �� #��[  6%   $
��Left=�6a   
���   ���5;   $   Z� ��6;   Z� .��5<   "   �� $��-5q�6-   .+�5=   &     $ ChatOutline=�6>   6S   5>   #   5?      5@      �� 5A      $��Center=�6'   $��0 60 0 0q�6D   .��5B      �� -   5         ��Name$ btnExit��5C   d   #��[  5B   %   $
��Left=�6a   
���   ���5C   $   Z� ��6;   Z� .��5D   "   �� $��-5q�6-   .+�5E   &     $ 	ExitToApp=�6>   6Q   5F   #   5G      5H      5I      5J      � 5K      -   5        $��RenderPages��5K   <   $��1��6   ���#F6F5F85M      5N      5O      5P      5Q      