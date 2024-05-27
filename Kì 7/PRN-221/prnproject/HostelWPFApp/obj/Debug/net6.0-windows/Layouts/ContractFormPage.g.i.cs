﻿#pragma checksum "..\..\..\..\Layouts\ContractFormPage.xaml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "FAE4C8FEAE6A49D3E3B0B2F7DB95ECA5A908E903"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using HostelWPFApp.Layout;
using HostelWPFApp.UserControls;
using MaterialDesignThemes.Wpf;
using MaterialDesignThemes.Wpf.Converters;
using MaterialDesignThemes.Wpf.Transitions;
using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Controls.Ribbon;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace HostelWPFApp.Layout {
    
    
    /// <summary>
    /// ContractFormPage
    /// </summary>
    public partial class ContractFormPage : System.Windows.Controls.UserControl, System.Windows.Markup.IComponentConnector {
        
        
        #line 71 "..\..\..\..\Layouts\ContractFormPage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal HostelWPFApp.UserControls.MyComboBox customerComboBox;
        
        #line default
        #line hidden
        
        
        #line 73 "..\..\..\..\Layouts\ContractFormPage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal HostelWPFApp.UserControls.MyComboBox roomComboBox;
        
        #line default
        #line hidden
        
        
        #line 75 "..\..\..\..\Layouts\ContractFormPage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal HostelWPFApp.UserControls.MyComboBox statusComboBox;
        
        #line default
        #line hidden
        
        
        #line 77 "..\..\..\..\Layouts\ContractFormPage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal HostelWPFApp.UserControls.MyDatePicker startDateDatePicker;
        
        #line default
        #line hidden
        
        
        #line 79 "..\..\..\..\Layouts\ContractFormPage.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal HostelWPFApp.UserControls.MyDatePicker endDateDatePicker;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "7.0.1.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/HostelWPFApp;component/layouts/contractformpage.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\..\..\Layouts\ContractFormPage.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "7.0.1.0")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
        internal System.Delegate _CreateDelegate(System.Type delegateType, string handler) {
            return System.Delegate.CreateDelegate(delegateType, this, handler);
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "7.0.1.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.customerComboBox = ((HostelWPFApp.UserControls.MyComboBox)(target));
            return;
            case 2:
            this.roomComboBox = ((HostelWPFApp.UserControls.MyComboBox)(target));
            return;
            case 3:
            this.statusComboBox = ((HostelWPFApp.UserControls.MyComboBox)(target));
            return;
            case 4:
            this.startDateDatePicker = ((HostelWPFApp.UserControls.MyDatePicker)(target));
            return;
            case 5:
            this.endDateDatePicker = ((HostelWPFApp.UserControls.MyDatePicker)(target));
            return;
            }
            this._contentLoaded = true;
        }
    }
}

